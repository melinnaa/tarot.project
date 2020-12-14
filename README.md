# Tarot project

## Introduction


## Analyse et modélisation
(Diagramme)
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



