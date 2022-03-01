package com.example.marvelsuperheroes.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelsuperheroes.R
import com.example.marvelsuperheroes.databinding.ActivityMainBinding
import com.example.marvelsuperheroes.utils.toast
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SuperheroViewModel by viewModels()
    private lateinit var searchView: SearchView

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
        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty())
                    lifecycleScope.launch {
                        viewModel.getSuperheroByNameCoincidence(query)
                    }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                binding.marvelLogo.visibility = View.INVISIBLE
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                binding.marvelLogo.visibility = View.VISIBLE
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_non_filtered -> {
                lifecycleScope.launch {
                    viewModel.getAllSuperheroes()
                }
                toast("Showing all superheroes")
                true
            }
            R.id.action_filtered -> {
                lifecycleScope.launch {
                    viewModel.getSuperHeroesWithImage()
                }
                toast("Showing only superheroes with images")
                true
            }
            R.id.action_search -> {
                true
            }
            else -> true
        }
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.onActionViewCollapsed()
            binding.marvelLogo.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }
}
