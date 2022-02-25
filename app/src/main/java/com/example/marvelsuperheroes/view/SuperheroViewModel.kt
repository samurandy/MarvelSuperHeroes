package com.example.marvelsuperheroes.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.network.SuperheroService
import kotlinx.coroutines.launch

class SuperheroViewModel : ViewModel() {
    private val service = SuperheroService()
    val superheroList = MutableLiveData<List<Superhero>>()
    var isLoading = MutableLiveData<Boolean>()
    var result: List<Superhero> = emptyList()

    suspend fun initView() {
        viewModelScope.launch {
            isLoading.postValue(true)
            result = service.getAllSuperheroes()
            superheroList.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getAllSuperheroes() {
        if (!result.isNullOrEmpty()) superheroList.postValue(result)
    }

    fun getSuperHeroesWithImage() {
        if (!result.isNullOrEmpty()) superheroList.postValue(
            result.filterNot { it.thumbnail.path.contains("image_not_available") }
        )
    }
}
