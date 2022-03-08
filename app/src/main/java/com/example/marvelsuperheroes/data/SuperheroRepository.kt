package com.example.marvelsuperheroes.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.network.SuperheroService
import com.example.marvelsuperheroes.utils.ConnectivityLiveData
import com.example.marvelsuperheroes.utils.Resource
import javax.inject.Inject

class SuperheroRepository @Inject constructor(private val service : SuperheroService) {

    suspend fun getAllSuperHeroes(): Resource<List<Superhero>> {
        return service.getAllSuperheroes()
    }

    suspend fun getSuperheroesByName(name: String): Resource<List<Superhero>> {
        return service.getSuperheroesByName(name = name)
    }
}
