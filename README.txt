================================================================================
                            PROJET FRIGOMALIN
                        Application Mobile Android
================================================================================

MEMBRES DU GROUPE
-----------------
1. SAIGHI Aymen      - ***      - CCC3
2. RABILLAT Adrien   - ***   - CCC3
3. SOUMATI Aboubakr  - ***  - CCC3
4. RONSERAY Antonin  - ***  - CCC3
5. RIDANE Eya        - ***        - CCC3

École : ESILV
Année académique : 2024-2025


DESCRIPTION DU PROJET
---------------------
FrigoMalin est une application Android qui vous aide à trouver des recettes avec les ingrédients que vous avez.  Elle utilise l’API publique TheMealDB pour récupérer et afficher les données de recettes.



FONCTIONNALITÉS CLÉS
--------------------
• Recherche de recettes par ingrédient
• Affichage des détails complets 
• Prise de photo via la caméra pour illustrer les recettes
• Partage de recettes via d'autres applications
• Support multilingue (Français / Anglais) on prend la langue systeme


CHOIX TECHNIQUES
----------------
L'application a été développée en respectant les bonnes pratiques Android :

Architecture :
  - Pattern MVVM  pour une séparation claire des couches
  - LiveData et ViewModel pour la gestion du cycle de vie
  - Repository pattern pour l'abstraction de la source de données

Technologies utilisées :
  - Kotlin 
  - Retrofit pour l'API REST
  - Coroutines pour la gestion asynchrone
  - ViewBinding pour l'accès aux vues
  - Navigation Component pour la navigation entre fragments

Configuration :
  - API cible : Android 15 
  - Émulateur testé : Pixel 9a
  - Code organisé selon les principes Clean Code


LIMITATIONS & BUGS CONNUS
--------------------------
Limitations techniques identifiées :

1. Recherche mono-ingrédient uniquement
      L’API TheMealDB gratuite ne permet pas de rechercher des recettes avec plusieurs ingrédients via son endpoint public.  La recherche est limitée à un seul ingrédient.  C’est une contrainte de l’API, pas un choix de design.

2. Photos non persistantes
   Les photos prises avec la caméra sont stockées temporairement en mémoire pour
   la démonstration mais ne sont pas sauvegardées en base de données. Cette 
   fonctionnalité nécessiterait l'implémentation d'une couche de persistance 
   locale (Room).

3. Connexion internet requise
   L'application ne dispose pas de cache local. Une connexion internet active
   est nécessaire pour effectuer les recherches.


INSTALLATION & EXÉCUTION
------------------------
1. Ouvrir le projet dans Android Studio
2. Synchroniser les dépendances Gradle
3. Lancer l'application sur un émulateur 
4. Connexion internet obligatoire


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
