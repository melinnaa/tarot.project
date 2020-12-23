# Tarot project

## Introduction


## Analyse et modélisation
(Diagramme)

Au lancement de l'application, une interface s'affiche. À travers celle-ci nous pouvons circuler entre les différentes fonctionnalités: création d'une carte, consultation de la collection de cartes, tirage de cartes, paramètres d'affichage.

Classes métiers:
- la classe Card qui correspond à une carte permet de lui ajouter des propriétés passées en paramètres (nom, description, image), de modifier ses propriétés, de lui attribuer un numéro automatiquement, d'effectuer des vérifications (sur des propriétés), de supprimer la carte.
- la classe Collection permet de trouver une carte par son nom ou son numéro, de trouver l'index d'une carte dans l'array, de remplacer une carte par une autre.
- la classe Draw permet de stimuler un tirage de Tarot.
- la classe FileChooser est utilisée pour exporter des fichiers (ici des images) depuis la machine locale.

Une classe d'interfaçage héritant de la classe JFrame sera dédiée à la gestion la mise en page de l'application. Celle-ci contiendra tous les objets permettant d'établir l'aspect graphique de l'application.
Pour plus de détails sur l'architecture de l'interface graphique aller à #Interface graphique.

## Système de carte basique

Le système de carte basique se retrouve dans la classe Card.
Une carte aura comme propriétés: 
- un nom
- un numéro
- une arcane
- une description 
- une image

Le numéro et le nom est unique pour chaque carte ce qui permettera ainsi d'identifier une carte à partir d'une de ces propriétés.
Une même arcane peut être utilisée par plusieurs cartes.

Le nom, l'arcane, la description son choisies par l'utilisateur.
Le numéro est attribué automatiquement par le programme (le plus petit numéro disponible est attribué à la carte qui va être créée).

Des vérifications s'effectuent pour voir si une carte peut bien être créée avec la méthode statique 

```canBeCreated(String name, String arcana, String description, File img)```

Celle-ci vérifie si:
- le nom que l'on souhaite ajouter à une carte n'a pas déjà été utilisé (appel de isNameUsed())
- l'arcane insérée en est bien une (appel de isArcana())
- la description et le fichier inséré ne sont pas égaux à null


## Extension et recherche

La classe Card ne pouvait que jusqu'à présent créer une carte et vérifier les propriétés insérées.

Nous rajoutons une méthode ```canBeUpdated(String name, String arcana, String description, File img)``` qui agira presque de la même façon que  ```canBeCreated(String name, String arcana, String description, File img)``` pour vérifier si les propriétés insérées sont valables afin de mettre à jour la carte. 

Après cette vérification nous rajoutons la méthode ```setProperties(String name, String arcana, String description, File img)``` pour mettre à jour la carte courante. Celle-ci contient des setters pour mettre individuellement à jour le nom, l'arcane, la description et l'image.

La nouvelle classe FileChooser permet à l'utilisateur de charger un fichier (dans notre cas le fichier devra être au format jpeg/png).
Cette classe sera instanciée lorsque nous développerons l'interface graphique: au clic sur un bouton, nous appelerons alors la classe.

On créer une autre classe Collection qui sera dédiée à la consultation de la collection de cartes.
Celle-ci contient un array qui contient lui même toutes les cartes créées par l'utilisateur.
Des méthodes permettent trouver une carte à partir de son nom ou de son numéro car ces propriétés sont uniques.

## Sauvegarde
Nous sauvegardons le paquet de carte du joueur grâce à la sérialisation en JSON.
Ce paquet contient des éléments de type Card.
Pour sérialiser on utilise Gson.
Pour désérialiser de même. J'ai implémenté un algorithme permettant de détecter en fonction des chaines de caractères du fichier json où est ce que commençait le code d'un objet Card.

À quels moments s'exécute la sérialisation/déserialisation ?
->
->
->
-> 
## Interface graphique
La classe Interface contient tous les panels nécessaires pour l'affichage de chaque section.
Elle hérite de la classe JFrame et est décomposée en 2 panels: le menu (header), le panel principal.
Composition des panels principaux:
- Le menu contient les boutons nécessaire à la navigation entre les différentes fonctionnalités de l'application
- Le panel principal contient tous les sous-panels dédiés à chaque section tous empilés les uns en-dessous des autres avec la CardLayout

Les sous-panels sectionnaires:
- CreationPanel: section de création de carte, l'utilisateur insère les propriétés qu'il souhaite et à la validation, une carte s'ajoute à sa collection
- CollectionPanel: section de recherche et gestion dans la collection, l'utilisateur effectue sa recherche en fonction de certaines propriétés ou en défilant entre les cartes. Dans cette même section, il pourra supprimer une carte et ouvrir une interface d'édition de carte.
- DrawPanel: section de tirage de cartes, stimulation d'un tirage du jeu de divination.
- ManagerPanel: section de paramètres, l'utilisateur aura accès à des paramètres d'interfaçage de l'application.

La classe abstraite PaintForm est un modèle pour les interfaces de création et d'édition de carte.
Ce modèle est sectionné en deux parties: 
- une partie de visualisation de carte : **objet de classe CardView**
- une partie de formulaire : **objet JPanel**

Comment la consigne de découplage entre les fonctionnalités et l'aspect d'interfaçage a été appliquée ?
Chacune de ces classes panels est conçue de façon à ce qu'elle ne soit qu'un canal entre les actions de l'utilisateur et les classes métiers.
Par exemple la classe CreationPanel va uniquement disposer les éléments de l'interface graphique et vérifier si les entrées de l'utilisateur sont valides pour pouvoir les traîter avec les méthodes de la classe métier Card. La vérification s'effectue avec des méthodes statiques de la classe Card (canBeCreated).

Comment un tiers peux rajouter une fonctionnalité ?
Si l'on souhaite rajouter une fonctionnalité il suffira de créer une méthode dans la classe métier Card, de la tester, puis de rajouter un élément graphique dans CreationPanel avec une action qui permettra d'allier les 2 classes.

## Lecture du futur
Une section supplémentaire a été créée pour permettre à l'utilisateur tirer des cartes de sa collection afin qu'elles puissent lui prédire son avenir.
Un tirage ne se déclenche que lorsqu'il existe au moins 4 cartes dans la collection.

**Quel tirage stimule le programme ?**
Le programme stimule un tirage en croix. 

**Dans ce tirage chaque carte s'interprète différemment en fonction de son placement.**
- Carte de gauche: état actuel du patient
- Carte de droite: le problème auquel se confrontera le patient
- Carte du haut: le conseil donnée par le tirage
- Carte du bas: prédiction du futur et réponse à la question

**Comment se déroule un tirage ?**
À partir du moment qu'il y a au moins 4 cartes dans la collection, un tirage se déclenche (instanciation de la classe Draw).
Le patient doit tout d'abord se poser une question.
Le tirage prélève 4 cartes au hasard parmi toutes les cartes de la collection. Celles-ci se placent du côté pile en croix.
À chaque clic sur une carte, celle-ci se retourne, dévoile son image et son interprétation (grâce à sa description) en fonction de son placement dans le tirage.

**Les fonctionnalités:**
L'utilisateur a à sa disposition un bouton qui lui permet de relancer des tirages à l'infini.
Le résultat du tirage perdure dans la section dédiée tant que l'utilisateur ne relance pas un tirage ou qu'il ne quitte pas l'application.

