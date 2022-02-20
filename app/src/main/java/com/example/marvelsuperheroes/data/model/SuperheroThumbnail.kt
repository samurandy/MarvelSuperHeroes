package com.example.marvelsuperheroes.data.model

import com.google.gson.annotations.SerializedName

data class SuperheroThumbnail(@SerializedName("path") var path: String, var extension: String)
