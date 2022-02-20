package com.example.marvelsuperheroes.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.network.SuperheroService
import kotlinx.coroutines.launch

class SuperheroViewModel: ViewModel() {
    private val service = SuperheroService()
    val superheroList = MutableLiveData<List<Superhero>>()

    suspend fun getAllSuperheroes(){
        viewModelScope.launch {
            val result = service.getAllSuperheroes()

            if (!result.isNullOrEmpty()) {
                superheroList.postValue(result)
            }
        }
    }

}