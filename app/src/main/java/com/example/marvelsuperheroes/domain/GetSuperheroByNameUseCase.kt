package com.example.marvelsuperheroes.domain

import com.example.marvelsuperheroes.data.SuperheroRepository
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.utils.Resource
import javax.inject.Inject

class GetSuperheroByNameUseCase @Inject constructor(private val repository : SuperheroRepository){

    suspend fun getSuperheroesByName(name: String): Resource<List<Superhero>> {
        return repository.getSuperheroesByName(name)
    }
}
