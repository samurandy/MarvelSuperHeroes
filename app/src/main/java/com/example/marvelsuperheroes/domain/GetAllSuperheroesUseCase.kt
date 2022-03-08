package com.example.marvelsuperheroes.domain

import android.app.Application
import android.content.Context
import com.example.marvelsuperheroes.data.SuperheroRepository
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.utils.Resource
import javax.inject.Inject

class GetAllSuperheroesUseCase @Inject constructor(private val repository : SuperheroRepository) {

    suspend fun getAllSuperHeroes(): Resource<List<Superhero>> {
        return repository.getAllSuperHeroes()
    }
}
