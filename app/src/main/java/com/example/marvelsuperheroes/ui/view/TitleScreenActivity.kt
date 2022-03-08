package com.example.marvelsuperheroes.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.marvelsuperheroes.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TitleScreenActivity : AppCompatActivity() {
    companion object {
        const val DELAY_TIME: Long = 2000
    }

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goToMainActivity()
    }

    private fun goToMainActivity() {
        lifecycleScope.launch {
            delay(DELAY_TIME)
            val intent = Intent(this@TitleScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
