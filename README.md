# Tarot project

## Introduction


## Analyse et modélisation
(Diagramme)

Au lancement de l'application, une interface s'affiche. À travers celle-ci nous pouvons circuler entre les différentes fonctionnalités: création d'une carte, consultation de la collection de cartes, paramètres d'affichage.
Dans l'interface de création de carte, l'utilisateur insère les propriétés qu'il souhaite et à la validation, une carte s'ajoute à sa collection.
Dans l'interface de recherche de carte, l'utilisateur effectue sa recherche en fonction de certaines propriétés ou en défilant entre les cartes. Dans cette même section, il pourra ouvrir une interface d'édition de carte et modifier les propriétés d'une carte.
Dans l'interface de paramètres l'utilisateur aura accès à des fonctionnalités de paramètres de l'application.

La classe abstraite PaintForm est un modèle pour les interfaces de création et d'édition de carte.
Ce modèle est sectionné en deux parties: une partie de visualisation de carte et une autre de formulaire.


Les interfaces de création et d'édition auront le même modèle de formulaire. Pour cela il existe une classe abstraite PaintForm qui est un modèle de formulaire de création ou d'édition de carte

classe Card pour gérer les actions que l'on peut réaliser avec une carte
classe SearchCard pour gérer les actions que l'on peut réaliser avec un ensemble de cartes, leurs interactions entre elles.
classe AppManager pour gérer certains paramètres de l'application

classe abstraite pour l'interface de creation et de modification car ces deux classes se ressemblent.
classe displayCard pour le panel d'affichage de carte qui sera la même pour la création, la modification et l'affichage

## Système de carte basique
Une carte aura comme propriétés: un nom, une arcane, une description et un numéro.
Le numéro est unique pour chaque carte ce qui permettera ainsi d'identifier une carte.
Plusieurs cartes peuvent avoir le même nom et la même arcane.
On pourra ajouter une image quelconque au format jpeg/png.
Tout le système de carte basique se trouvera dans la classe Card.
On a une méthode pour ajouter le nom, l'arcane et la description d'une carte.
On a également une méthode pour supprimer la carte courante.

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
