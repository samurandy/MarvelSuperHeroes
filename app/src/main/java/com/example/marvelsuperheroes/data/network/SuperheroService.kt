package com.example.marvelsuperheroes.data.network

import android.util.Log
import com.example.marvelsuperheroes.data.model.SuperheroResponse
import com.example.marvelsuperheroes.data.model.Superhero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class SuperheroService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllSuperheroes(): List<Superhero> {
        return withContext(Dispatchers.IO) {
            val response: Response<SuperheroResponse> =
                retrofit.create(SuperheroApiClient::class.java).getAllSuperheroes()
            response.body()?.data?.results ?: emptyList()
        }
    }

    /*suspend fun getSuperhero(id: Int): Superhero {
        return withContext(Dispatchers.IO) {
            val response: Response<Superhero> =
                retrofit.create(SuperheroApiClient::class.java).getSuperhero(id)
            response.body() ?: Superhero("", "", )
        }
    }*/
}

