package com.example.marvelsuperheroes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: SuperheroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerPokemon.layoutManager = GridLayoutManager(this,2)
        viewModel.superheroList.observe(this,{
            binding.recyclerPokemon.adapter = SuperheroAdapter(it)
        })

        getAllSuperHeroes()
    }

    private fun getAllSuperHeroes() {
        lifecycleScope.launch{
            viewModel.getAllSuperheroes()
        }
    }
}