package com.example.marvelsuperheroes.data.model

import com.google.gson.annotations.SerializedName

data class SuperheroData(var limit: Int, @SerializedName("results") var results: List<Superhero>)