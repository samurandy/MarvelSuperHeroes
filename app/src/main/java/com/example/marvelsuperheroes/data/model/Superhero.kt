package com.example.marvelsuperheroes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Superhero(
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("thumbnail") var thumbnail: SuperheroThumbnail
) : Parcelable
