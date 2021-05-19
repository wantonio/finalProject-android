package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.databinding.ListCellBinding
import com.example.finalProject.extensions.loadSvg
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.models.PokemonListItemRecent
import io.reactivex.rxjava3.core.Observable

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    lateinit var clickListener: (pokemonName: String) -> Unit
    var onEmptyList: (() -> Unit)? = null
    var totalCount = 0
    var addFavorite: ((pokemon: PokemonListItem, position: Int, shouldAdd: Boolean) -> Unit)? = null
    var addFavoriteRec: ((pokemon: PokemonListItem, position: Int, shouldAddFavRec: Boolean) -> Unit)? = null
    var addRecent: ((pokemon: PokemonListItem, position: Int, shouldAdd: Boolean) -> Unit)? = null
    var pokemons = mutableListOf<PokemonListItem>()
   // var pokemonsRecent = mutableListOf<PokemonListItemRecent>()

        set(value) {
            field = value

            if (field.isEmpty()) {
                onEmptyList?.invoke()
            }

            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(private val binding: ListCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonListItem, imageUrl: String, position: Int) {
            binding.namePokemon.text = pokemon.name
            binding.imageView.loadSvg(imageUrl)
              binding.root.setOnClickListener {
                clickListener(pokemon.name)
                 addRecent?.invoke(pokemon, position, true)

             }

            binding.toggleButton.isChecked = pokemon.isFavorite


            binding.toggleButton.setOnClickListener{
                val isChecked = binding.toggleButton.isChecked
                pokemon.isFavorite = isChecked
                addFavorite?.invoke(pokemon, position, isChecked)
            }




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
       // val pokemonR = pokemonsRecent[position]
        val idPattern = Regex("""(\d+)/$""")
        val pokemonId =  idPattern.find(pokemon.url)?.groupValues?.get(1) ?: "1"
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$pokemonId.svg"
        holder.bind(pokemon, imageUrl, position)
    }

    override fun getItemCount(): Int = pokemons.size

    fun setOnItemClickListener(clickListener: (name: String) -> Unit) {
        this.clickListener = clickListener
    }
}