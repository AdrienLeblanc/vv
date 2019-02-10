# Rapport du projet de Vérification et Validation

Erwan IQUEL - Adrien LEBLANC

## Introduction

Projet *Génération d'assertion* du cours de Vérification et Validation, du Master 2 Ingénierie Logicielle, ISTIC, Rennes.

Lors de ce projet il nous a été demandé de développé en *Java*, un système qui génère un certain nombre d'assertions en plus dans les méthodes de tests, ceci afin d'améliorer leur robustesse.

Vous trouverez à la racine du projet: 
* Un *README* (contenant l'ensemble des outils utilisé) ; 
* Des indications pour comment lancé le projet ; 
* Mais aussi le code source du projet (et son jeu de test).

## Solution

Pour ce Projet nous avons utilisé la bibliothèque *Spoon* afin de faire l'analyse et la génération de notre code.

### Architecture

Nous sommes partie sur un principe de 3 componsents (le même que pour [Spoon at OW2Con'17](https://github.com/SpoonLabs/spoon-examples/blob/master/docs/spoon_ow2Con_2017.pdf)):

* Un Analyzer ;
* Un Collecter ;
* Un Adder.

La différence se fait au niveau de l'Analyzer et de l'Adder, qui respectivement:

* Filtre par rapport au nom de méthode donné en paramètre du jar ;
* Ajoute N assertions, qui est le nombre d'assertions spécifié en paramètre du jar (si rien n'est donné, alors 2 assertions sont générées par défaut).

## Bilan

