package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.databinding.ListCellBinding
import com.example.finalProject.extensions.loadSvg
import com.example.finalProject.models.PokemonListItem

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private var _binding: ListCellBinding? = null
    private val binding get() = _binding!!
    lateinit var clickListener: (pokemonId: Int) -> Unit
    var onEmptyList: (() -> Unit)? = null

    var totalCount = 0
    var pokemons: List<PokemonListItem> = emptyList()
        set(value) {
            field = value

            if (field.isEmpty()) {
                onEmptyList?.invoke()
            }

            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(binding: ListCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, imageUrl: String) {
            binding.namePokemon.text = name
            binding.imageView.loadSvg(imageUrl)
            itemView.setOnClickListener {
                clickListener(2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        _binding = ListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        val idPattern = Regex("""(\d)/$""")
        val pokemonId =  idPattern.find(pokemon.url)?.groupValues?.get(1) ?: "1"
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$pokemonId.svg"
        holder.bind(pokemon.name, imageUrl)
    }

    override fun getItemCount(): Int = pokemons.size

    fun setOnItemClickListener(clickListener: (id: Int) -> Unit) {
        this.clickListener = clickListener
    }
}