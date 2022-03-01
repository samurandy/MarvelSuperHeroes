package com.example.marvelsuperheroes.data.network

import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.model.SuperheroResponse
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
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

    suspend fun getSuperheroesByName(
        name: String
    ): List<Superhero> {
        return withContext(Dispatchers.IO) {
            val response: Response<SuperheroResponse> =
                retrofit.create(SuperheroApiClient::class.java)
                    .getSuperheroesByName(search = name)
            response.body()?.data?.results ?: emptyList()
        }
    }
}
