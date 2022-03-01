package com.example.marvelsuperheroes.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelsuperheroes.data.model.Superhero
import com.example.marvelsuperheroes.databinding.ItemCardBinding
import com.example.marvelsuperheroes.utils.Constants.Companion.DOT
import com.example.marvelsuperheroes.utils.Constants.Companion.IMAGE_LARGE_SIZE
import com.example.marvelsuperheroes.utils.glide

class SuperheroAdapter(private var superheroList: List<Superhero>) :
    RecyclerView.Adapter<SuperheroAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // fill recyclerview
        with(holder) {
            with(superheroList[position]) {
                binding.name.text = name.replaceFirstChar { it.uppercase() }
                holder.binding.marvelImageMain.glide(
                    "${thumbnail.path}${IMAGE_LARGE_SIZE}${DOT}${thumbnail.extension}"
                )
            }
        }
        // ClickListener in recycler from Main to Detail
        holder.binding.itemCard.setOnClickListener { v ->
            val intent = Intent(v.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_ID, superheroList[position])
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return superheroList.size
    }
}
