package com.example.marvelsuperheroes.domain

import com.example.marvelsuperheroes.data.SuperheroRepository
import com.example.marvelsuperheroes.data.model.Superhero

class GetSuperheroUseCase {
    private val repository = SuperheroRepository()

    suspend fun getSuperheroesByName(name: String): List<Superhero> {
        return repository.getSuperheroesByName(name)
    }
}
