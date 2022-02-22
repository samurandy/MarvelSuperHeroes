package com.example.marvelsuperheroes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperheroThumbnail(@SerializedName("path") var path: String, var extension: String) :
    Parcelable
