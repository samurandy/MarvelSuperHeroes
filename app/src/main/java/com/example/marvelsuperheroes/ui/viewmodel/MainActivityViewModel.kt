package com.example.marvelsuperheroes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.domain.GetAllSuperheroesUseCase
import com.example.marvelsuperheroes.domain.GetSuperheroByNameUseCase
import com.example.marvelsuperheroes.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllSuperheroesUseCase : GetAllSuperheroesUseCase,
    private val getSuperheroUseCase : GetSuperheroByNameUseCase): ViewModel() {
    val superheroListLiveData = MutableLiveData<List<Superhero>>()
    var isLoading = MutableLiveData<Boolean>()
    private var superheroList: List<Superhero> = emptyList()
    val error = MutableLiveData<String>()

    suspend fun initView() {
        isLoading.postValue(true)
        when (val result = getAllSuperheroesUseCase.getAllSuperHeroes()) {
            is Resource.Success -> {
                result.data?.let { superheroList = it }
                superheroListLiveData.postValue(result.data)}
            is Resource.Error -> error.postValue(result.message)
        }
        isLoading.postValue(false)
    }

    fun getAllSuperheroes() {
        if (!superheroList.isNullOrEmpty()) superheroListLiveData.postValue(superheroList)
    }

    fun getSuperHeroesWithImage() {
        if (!superheroList.isNullOrEmpty()) superheroListLiveData.postValue(
            superheroList.filterNot { it.thumbnail.path.contains("image_not_available") }
        )
    }

    suspend fun getSuperheroByNameCoincidence(name: String) {
        isLoading.postValue(true)
        when (val result = getSuperheroUseCase.getSuperheroesByName(name)) {
            is Resource.Success -> superheroListLiveData.postValue(result.data)
            is Resource.Error -> error.postValue(result.message)
        }
        isLoading.postValue(false)
    }
}
