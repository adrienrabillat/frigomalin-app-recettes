================================================================================
                            PROJET FRIGOMALIN
                        Application Mobile Android
================================================================================

MEMBRES DU GROUPE
-----------------
1. SAIGHI Aymen      - ***      - [Classe]
2. RABILLAT Adrien   - ***   - CCC3
3. SOUMATI Aboubakr  - ***  - [Classe]
4. RONSERAY Antonin  - ***  - [Classe]
5. RIDANE Eya        - ***        - [Classe]

École : ESILV
Année académique : 2024-2025


DESCRIPTION DU PROJET
---------------------
FrigoMalin est une application Android d'assistance culinaire qui permet de 
rechercher des recettes en fonction des ingrédients disponibles. L'application 
consomme l'API publique TheMealDB pour récupérer et afficher les données de 
recettes.


FONCTIONNALITÉS CLÉS
--------------------
• Recherche de recettes par ingrédient
• Affichage des détails complets (instructions, ingrédients, image)
• Prise de photo via la caméra pour illustrer les recettes
• Partage de recettes via d'autres applications
• Support multilingue (Français / Anglais)


CHOIX TECHNIQUES
----------------
L'application a été développée en respectant les bonnes pratiques Android :

Architecture :
  - Pattern MVVM (Model-View-ViewModel) pour une séparation claire des couches
  - LiveData et ViewModel pour la gestion du cycle de vie
  - Repository pattern pour l'abstraction de la source de données

Technologies utilisées :
  - Kotlin comme langage principal
  - Retrofit pour la communication avec l'API REST
  - Coroutines pour la gestion asynchrone
  - ViewBinding pour l'accès type-safe aux vues
  - Navigation Component pour la navigation entre fragments

Configuration :
  - API cible : Android 15 (API 36)
  - Émulateur testé : Pixel 9a
  - Code organisé selon les principes Clean Code


LIMITATIONS & BUGS CONNUS
--------------------------
Limitations techniques identifiées :

1. Recherche mono-ingrédient uniquement
   L'API TheMealDB en version gratuite ne propose pas de filtre multi-ingrédients
   via endpoint public. La recherche est donc volontairement limitée à un seul
   ingrédient à la fois. C'est une contrainte de l'API, pas un choix de design.

2. Photos non persistantes
   Les photos prises avec la caméra sont stockées temporairement en mémoire pour
   la démonstration mais ne sont pas sauvegardées en base de données. Cette 
   fonctionnalité nécessiterait l'implémentation d'une couche de persistance 
   locale (Room) qui sort du scope initial du projet.

3. Connexion internet requise
   L'application ne dispose pas de cache local. Une connexion internet active
   est nécessaire pour effectuer les recherches.


INSTALLATION & EXÉCUTION
------------------------
1. Ouvrir le projet dans Android Studio
2. Synchroniser les dépendances Gradle
3. Lancer l'application sur un émulateur (Pixel 9a recommandé) ou device physique
4. Connexion internet requise pour le fonctionnement


STRUCTURE DU PROJET
-------------------
app/
├── data/           # Couche de données (API, modèles)
├── ui/             # Couche présentation (Fragments, ViewModels)
│   ├── detail/
│   ├── landing/
│   ├── readme/
│   └── search/
└── res/            # Ressources (layouts, strings, drawables)


API UTILISÉE
------------
TheMealDB API - https://www.themealdb.com/api.php
Endpoints utilisés :
  - Search by ingredient: /filter.php?i={ingredient}
  - Lookup recipe details: /lookup.php?i={id}
