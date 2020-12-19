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
- la classe Card qui correspond à une carte permet de lui ajouter des propriétés passées en paramètres (nom, numéro, description, image), de modifier ses propriétés, d'effectuer des vérifications (sur des propriétés), de supprimer la carte
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

Le numéro et le nom est unique pour chaque carte ce qui permettera ainsi d'identifier une carte à partir d'une de ces propriétés.
Une même arcane peut être utilisée par plusieurs cartes.

Les vérifications s'effectuent pour voir si une carte peut bien être créée ou modifiée.
Ces vérifications utilisent des méthodes de vérifications plus précises:
- si le nom que l'on souhaite ajouter à une carte a déjà été utilisé
- si l'arcane insérée en est bien une: on vérifie si cette arcane est présente dans le tableau d'arcanes

On pourra ajouter une image quelconque au format jpeg/png.

## Extension et recherche
Dans la classe Card on ajoute une méthode qui puisse mettre à jour la carte courante.
On pourra mettre à jour le nom, l'arcana et la description.
On ajoute également la fonctionnalité qui permet de choisir une image locale pour la carte courante.

On créer une autre classe SearchCard qui sera dédiée à la gestion de l'ensemble des cartes.
C'est cette classe que l'on appelera pour consulter notre collection de cartes. On l'utilisera pour rechercher une classe en fonction de certaines propriétés (nom et numéro)

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
