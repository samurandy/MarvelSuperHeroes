package com.example.marvelsuperheroes.domain

import com.example.marvelsuperheroes.data.SuperheroRepository
import com.example.marvelsuperheroes.data.model.Superhero

class GetAllSuperheroesUseCase {
    private val repository = SuperheroRepository()

    suspend fun getAllSuperHeroes(): List<Superhero> {
        return repository.getAllSuperHeroes()
    }
}
