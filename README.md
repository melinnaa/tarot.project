# Tarot project

## Introduction


## Analyse et modélisation
(Diagramme)
L'application permet de créer des cartes avec ses propriétés. L'utilisateur a une interface dans laquelle un menu lui permet de choisir de créer une nouvelle carte ou de consulter sa collection de cartes
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
