package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.databinding.PokemonEvolutionBinding
import com.example.finalProject.extensions.loadMaybeSvg
import com.example.finalProject.models.Pokemon

class EvolutionAdapter : RecyclerView.Adapter<EvolutionAdapter.EvolutionViewHolder>() {
    var onItemClick: ((name: String) -> Unit)? = null
    var pokemons: List<Pokemon> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    inner class EvolutionViewHolder(private val binding: PokemonEvolutionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.evolutionName.text = pokemon.name
            binding.evolutionImage.loadMaybeSvg(pokemon.sprites.other.dream_world.front_default, pokemon.sprites.front_default)

            binding.root.setOnClickListener {
                onItemClick?.invoke(pokemon.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        return EvolutionViewHolder(PokemonEvolutionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int = pokemons.size
}