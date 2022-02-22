package com.example.marvelsuperheroes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ID = "superhero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillSuperheroDetail()
    }

    private fun fillSuperheroDetail() {
        intent.getParcelableExtra<Superhero>(EXTRA_ID).let {
            with(binding) {
                nameDetail.text = it?.name

                if (it?.description != "") descriptionDetail.text = it?.description
                else descriptionDetail.text = getString(R.string.Nodescription)

                lifecycleScope.launch {
                    Glide.with(this@DetailActivity)
                        .load("${it?.thumbnail?.path}.${it?.thumbnail?.extension}")
                        .into(imagePokemonDetail)
                }
            }
        }
    }
}