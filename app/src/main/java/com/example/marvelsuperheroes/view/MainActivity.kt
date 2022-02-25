package com.example.marvelsuperheroes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SuperheroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recycler.layoutManager = GridLayoutManager(this, 2)

        viewModel.superheroList.observe(this, {
            binding.recycler.adapter = SuperheroAdapter(it)
        })

        viewModel.isLoading.observe(this, {
            binding.progressBar.isVisible = it
        })

        lifecycleScope.launch {
            viewModel.initView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_non_filtered -> {
                lifecycleScope.launch {
                    viewModel.getAllSuperheroes()
                }
                Toast.makeText(applicationContext, "Showing all superheroes", Toast.LENGTH_LONG)
                    .show()
                true
            }
            R.id.action_filtered -> {
                lifecycleScope.launch {
                    viewModel.getSuperHeroesWithImage()
                }
                Toast.makeText(
                    applicationContext,
                    "Showing only superheroes with images",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}