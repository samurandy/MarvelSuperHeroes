package com.example.marvelsuperheroes.domain

import android.app.Application
import android.content.Context
import com.example.marvelsuperheroes.data.SuperheroRepository
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.utils.Resource

class GetAllSuperheroesUseCase {
    private val repository = SuperheroRepository()

    suspend fun getAllSuperHeroes(): Resource<List<Superhero>> {
        return repository.getAllSuperHeroes()
    }
}
