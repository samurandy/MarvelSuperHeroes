package com.example.marvelsuperheroes.data.model


import com.google.gson.annotations.SerializedName

data class Superhero(
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("thumbnail") var thumbnail: SuperheroThumbnail
)
