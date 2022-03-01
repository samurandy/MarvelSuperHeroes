package com.example.marvelsuperheroes.data

import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.network.SuperheroService

class SuperheroRepository {
    private val service = SuperheroService()

    suspend fun getAllSuperHeroes(): List<Superhero> {
        return service.getAllSuperheroes()
    }

    suspend fun getSuperheroesByName(name: String): List<Superhero> {
        return service.getSuperheroesByName(name = name)
    }
}
