package com.example.marvelsuperheroes.data.network

import com.example.marvelsuperheroes.data.model.SuperheroResponse
import com.example.marvelsuperheroes.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface SuperheroApiClient {

    @GET("characters")
    suspend fun getAllSuperheroes(
        @Query("ts") ts: String = Constants.ts,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit: Int = Constants.limit
    ): Response<SuperheroResponse>

    @GET("characters")
    suspend fun getSuperheroesByName(
        @Query("ts") ts: String = Constants.ts,
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("hash") hash: String = Constants.hash(),
        @Query("limit") limit: Int = Constants.limit,
        @Query("nameStartsWith") search: String
    ): Response<SuperheroResponse>
}
