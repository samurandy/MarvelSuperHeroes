package com.example.marvelsuperheroes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.marvelsuperheroes.data.network.SuperheroService
import com.example.marvelsuperheroes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var service = SuperheroService()

    companion object {
        const val EXTRA_ID = "position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillSuperheroDetail()
    }

    private fun fillSuperheroDetail() {
        val position = intent.getIntExtra(EXTRA_ID, -1)
        lifecycleScope.launch {
            /*with(service.getSuperhero(position + 1)) {
                with(binding) {


                    Glide.with(this@DetailActivity).load("$IMAGE_BASE_URL${position + 1}.png")
                        .into(imagePokemonDetail)
                }
            }*/
        }
    }
}