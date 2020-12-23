# Tarot project

## Introduction


## Analyse et modélisation
(Diagramme)

Au lancement de l'application, une interface s'affiche. À travers celle-ci nous pouvons circuler entre les différentes fonctionnalités: création d'une carte, consultation de la collection de cartes, paramètres d'affichage.

L'interface principale contient: 
- une section de création de carte (**instance de classe Creation**): l'utilisateur insère les propriétés qu'il souhaite et à la validation, une carte s'ajoute à sa collection.
- une section de recherche de carte (**instance de classe Search**): l'utilisateur effectue sa recherche en fonction de certaines propriétés ou en défilant entre les cartes. Dans cette même section, il pourra ouvrir une interface d'édition de carte et modifier les propriétés d'une carte.
- une section de paramètres (**instance de classe AppManager**)l'utilisateur aura accès à des fonctionnalités de paramètres de l'application.

La classe abstraite PaintForm est un modèle pour les interfaces de création et d'édition de carte.
Ce modèle est sectionné en deux parties: 
- une partie de visualisation de carte : **objet de classe CardView**
- une partie de formulaire : **objet JPanel**

Indépendamment de l'interface:
- la classe Card qui correspond à une carte permet de lui ajouter des propriétés passées en paramètres (nom, description, image), de modifier ses propriétés, de lui attribuer un numéro automatiquement, d'effectuer des vérifications (sur des propriétés), de supprimer la carte.
- la classe Collection permet de trouver une carte par son nom ou son numéro, de trouver l'index d'une carte dans l'array, de remplacer une carte par une autre.
- la classe FileChooser est utilisée pour exporter des fichiers (ici des images) depuis la machine locale.

classe abstraite pour l'interface de creation et de modification car ces deux classes se ressemblent.
classe displayCard pour le panel d'affichage de carte qui sera la même pour la création, la modification et l'affichage

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

Les vérifications s'effectuent pour voir si une carte peut bien être créée.
Ces vérifications utilisent des méthodes de vérifications plus précises:
- si le nom que l'on souhaite ajouter à une carte a déjà été utilisé
- si l'arcane insérée en est bien une: on vérifie si cette arcane est présente dans le tableau d'arcanes


## Extension et recherche

La classe Card ne pouvait que jusqu'à présent créer une carte et vérifier les propriétés insérées.

Nous rajoutons une fonctionnalité pour mettre à jour la carte courante.
De nouvelles méthodes permettent de mettre à jour le nom, l'arcane et la description.

On rajoute également une méthode qui permet d'ajouter une image locale.
La nouvelle classe FileChooser permettera à l'utilisateur de charger un fichier (dans notre cas le fichier devra être au format jpeg/png)

On créer une autre classe Collection qui sera dédiée à la consultation de la collection de cartes.
Celle-ci contient un array qui contient lui même toutes les cartes créées par l'utilisateur.
Des méthodes permettent trouver une carte à partir de son nom ou de son numéro.

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

