<h1 align="center">MarvelSuperHeroes</h1>

<p align="center">  
MarvelSuperHeroes is an application based on modern Android application tech-stacks using MVVM pattern and Clean Architecture.<br>
</p>
</br>


![Presentación1](https://user-images.githubusercontent.com/21971474/157326371-2e3148b2-0057-4baa-aa19-93aeb6f52564.png)

## Functionality
- Move around default 100 superheroes where is possible to filter them by only with image.
- Search your favourite superhero from all database.
- Navigate to superheroes detail in both cases above.

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
- Architecture
  - MVVM Pattern (View - DataBinding - ViewModel - Model)
  - Clean Architecture
  - [DaggerHilt](https://github.com/google/dagger/tree/master/java/dagger/hilt) - dependency injection.
- [Retrofit2 & Gson](https://github.com/square/retrofit) - construct the REST APIs.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [MotionEditor](https://developer.android.com/studio/write/motion-editor?hl=es-419) - implementing transformation motion animations.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.

## Unit Testing Frameworks
Unit Tests verify the interactions of viewmodels between repositories and REST api requests.
- [JUnit4](https://github.com/junit-team/junit4) - a simple framework to write repeatable tests.
- [Mockk](https://github.com/mockk/mockk) - a technique to make testing code readable and maintainable.

## Architecture
MarvelHeroes is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/21971474/157321132-fd1e56de-508d-453f-81e8-af605185943a.png)

<img src="https://user-images.githubusercontent.com/24237865/141415477-d1af2b48-2498-4ff0-8fdf-95dff092e317.png" align="right" width="22%"/>

## Content Credits
All copyrights of the contents, concepts, and phrases that are used in this open-source project belong to [Marvel Studios](https://www.marvel.com/).

# License
```xml
Designed and developed by 2022 samurandy (Andrés Carrasco)
