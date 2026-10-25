# FrigoMalin

Application Android qui propose des recettes à partir des ingrédients qu'on a dans son frigo, en s'appuyant sur l'API publique TheMealDB.

Projet de groupe réalisé en école d'ingénieurs (2024-2025) avec Aymen Saighi, Aboubakr Soumati, Antonin Ronseray et Eya Ridane.

**Vidéo de démonstration** : [FrigoMalin-Démo.mov](FrigoMalin-Démo.mov)

## Fonctionnalités

- Recherche de recettes par ingrédient, puis fiche détaillée de chaque recette
- Photo prise avec la caméra pour illustrer une recette
- Partage d'une recette vers d'autres applications
- Interface en français ou en anglais selon la langue du téléphone

## Choix techniques

- Architecture **MVVM** : ViewModel et LiveData pour le cycle de vie, pattern Repository pour isoler la source de données
- **Kotlin**, coroutines pour l'asynchrone, **Retrofit** pour l'API REST
- ViewBinding, Navigation Component (fragments `landing`, `search`, `detail`)
- Cible Android 15, testé sur émulateur Pixel 9a

## Limites connues

- Recherche sur un seul ingrédient à la fois : l'API gratuite de TheMealDB ne permet pas la recherche multi-ingrédients.
- Les photos ne sont pas persistées (il faudrait une base locale Room).
- Pas de cache hors ligne : une connexion internet est nécessaire.

## Lancer le projet

Ouvrir le dossier dans Android Studio, synchroniser Gradle, puis lancer sur un émulateur connecté à internet.

API utilisée : [TheMealDB](https://www.themealdb.com/api.php) (`/filter.php?i={ingrédient}`, `/lookup.php?i={id}`).
