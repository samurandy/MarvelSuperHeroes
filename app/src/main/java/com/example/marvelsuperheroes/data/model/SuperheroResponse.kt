package com.example.marvelsuperheroes.data.model

import com.google.gson.annotations.SerializedName

data class SuperheroResponse(val copyright: String, @SerializedName("data") val data: SuperheroData? = null)
