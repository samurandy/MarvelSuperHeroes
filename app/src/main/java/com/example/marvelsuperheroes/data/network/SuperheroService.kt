package com.example.marvelsuperheroes.data.network

import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.model.SuperheroResponse
import com.example.marvelsuperheroes.utils.Resource
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class SuperheroService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllSuperheroes(): Resource<List<Superhero>> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<SuperheroResponse> =
                    retrofit.create(SuperheroApiClient::class.java).getAllSuperheroes()
                Resource.Success(response.body()?.data?.results  ?: emptyList())
            } catch (e: Exception) {

                Resource.Error("There was a problem in the server")
            }
        }
    }

    suspend fun getSuperheroesByName(
        name: String
    ): Resource<List<Superhero>> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<SuperheroResponse> =
                    retrofit.create(SuperheroApiClient::class.java)
                        .getSuperheroesByName(search = name)
                Resource.Success(response.body()?.data?.results ?: emptyList())
            } catch (e: Exception) {
                Resource.Error("There was a problem in the server")
            }
        }
    }
}
