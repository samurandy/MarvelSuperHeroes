package com.example.marvelsuperheroes.ui.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.marvelsuperheroes.ui.viewmodel.MainActivityViewModel
import com.example.marvelsuperheroes.utils.ConnectivityLiveData
import com.example.marvelsuperheroes.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var searchView: SearchView
    private lateinit var checkConnection: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        checkConnection = ConnectivityLiveData(application)
        Log.d("***checkConnection1", checkConnection.value.toString())

        binding.recycler.layoutManager = GridLayoutManager(this, 2)

        viewModel.superheroListLiveData.observe(this, {
            binding.recycler.adapter = SuperheroAdapter(it)
        })

        viewModel.isLoading.observe(this, {
            binding.progressBar.isVisible = it
        })

        checkConnection.observe(this, { isConnected ->
            when (isConnected) {
                true -> if (viewModel.superheroListLiveData.value.isNullOrEmpty())
                    lifecycleScope.launch { viewModel.initView() }

                false -> toast("Network not available")
            }
        })

        viewModel.error.observe(this, {
            toast(it)
        })
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
                        searchItem.collapseActionView()
                        if (checkConnection.value == true ) viewModel.getSuperheroByNameCoincidence(query)
                        else toast("Network not available")
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
                true
            }
            R.id.action_filtered -> {
                lifecycleScope.launch {
                    viewModel.getSuperHeroesWithImage()
                }
                true
            }
            R.id.action_search -> true

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
