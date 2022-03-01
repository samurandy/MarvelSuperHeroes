package com.example.marvelsuperheroes.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.domain.GetAllSuperheroesUseCase
import com.example.marvelsuperheroes.domain.GetSuperheroUseCase

class SuperheroViewModel : ViewModel() {
    private val getAllSuperheroesUseCase = GetAllSuperheroesUseCase()
    private val getSuperheroUseCase = GetSuperheroUseCase()
    val superheroList = MutableLiveData<List<Superhero>>()
    var isLoading = MutableLiveData<Boolean>()
    private var result: List<Superhero> = emptyList()

    suspend fun initView() {
        isLoading.postValue(true)
        result = getAllSuperheroesUseCase.getAllSuperHeroes()
        superheroList.postValue(result)
        isLoading.postValue(false)
    }

    fun getAllSuperheroes() {
        if (!result.isNullOrEmpty()) superheroList.postValue(result)
    }

    fun getSuperHeroesWithImage() {
        if (!result.isNullOrEmpty()) superheroList.postValue(
            result.filterNot { it.thumbnail.path.contains("image_not_available") }
        )
    }

    suspend fun getSuperheroByNameCoincidence(name: String) {
        isLoading.postValue(true)
        val superheroListWithNameCoincidence = getSuperheroUseCase.getSuperheroesByName(name)
        superheroList.postValue(superheroListWithNameCoincidence)
        isLoading.postValue(false)
    }
}
