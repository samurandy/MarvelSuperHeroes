package com.example.marvelsuperheroes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.data.network.SuperheroService
import com.example.marvelsuperheroes.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var superheroList: List<Superhero>
    private var service = SuperheroService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerPokemon.layoutManager = GridLayoutManager(this,2)
        lifecycleScope.launch {
            superheroList = service.getAllSuperheroes()
            binding.recyclerPokemon.adapter = SuperheroAdapter(superheroList)
        }

    }
}