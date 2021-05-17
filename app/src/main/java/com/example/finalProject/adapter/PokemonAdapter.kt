package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.databinding.ListCellBinding
import com.example.finalProject.extensions.loadSvg
import com.example.finalProject.fragments.HomeAllFragmentDirections
import com.example.finalProject.models.PokemonListItem

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    lateinit var clickListener: (pokemonName: String) -> Unit
    var onEmptyList: (() -> Unit)? = null

    var totalCount = 0
    var addRecent: ((pokemon: PokemonListItem, position: Int, shouldAdd: Boolean) -> Unit)? = null
    var pokemons: List<PokemonListItem> = emptyList()
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




        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(ListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
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