package com.example.marvelsuperheroes.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.databinding.ActivityDetailBinding
import com.example.marvelsuperheroes.utils.Constants.Companion.DOT
import com.example.marvelsuperheroes.utils.Constants.Companion.IMAGE_XLARGE_SIZE
import com.example.marvelsuperheroes.utils.loadUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                else descriptionDetail.text = getString(R.string.no_description)

                lifecycleScope.launch {
                    imageDetail.loadUrl("${it?.thumbnail?.path}${IMAGE_XLARGE_SIZE}${DOT}${it?.thumbnail?.extension}")
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }
}
