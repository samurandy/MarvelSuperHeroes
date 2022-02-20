package com.example.marvelsuperheroes.data.network

import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.model.SuperheroResponse
import com.example.marvelsuperheroes.utils.Constants
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface SuperheroApiClient {

    @GET("characters")
    suspend fun getAllSuperheroes(
        @Query("ts") ts: String = Constants.ts,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit: String = Constants.limit
    ): Response<SuperheroResponse>

    @GET("characters/{characterId}")
    suspend fun getSuperhero(@Path("id") id: Int): Response<Superhero>


}