package com.example.marvelsuperheroes.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.databinding.ActivityDetailBinding
import com.example.marvelsuperheroes.utils.Constants
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
                Log.d("***superheroUrl","${it?.thumbnail?.path}")
                lifecycleScope.launch {
                    imageDetail.glide("${it?.thumbnail?.path}${Constants.IMAGE_XLARGE_SIZE}.${it?.thumbnail?.extension}")
                }
            }
        }
    }

    fun ImageView.glide(url: String) {
        Glide.with(this).load(url).fitCenter().into(this)
    }
}

