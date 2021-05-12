package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.models.Contact
import com.example.finalProject.databinding.ListCellBinding
import com.example.finalProject.models.PokemonListItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

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

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, imageUrl: String) {
            binding.namePokemon.text = name
            Picasso.get().load(imageUrl).into(binding.imageView)
            itemView.setOnClickListener {
                clickListener(2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        _binding = ListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        val idPattern = Regex("""(\d)/$""")
        val pokemonId =  idPattern.find(pokemon.url)?.groupValues?.get(1)
        val imageUrl =
            if (pokemonId != null) "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$pokemonId.svg"
            else "https://cdn.shopify.com/s/files/1/0941/8552/products/Pokemon_-_Pokeball_large.jpg"
        holder.bind(pokemon.name, imageUrl)
    }

    override fun getItemCount(): Int = pokemons.size

    fun setOnItemClickListener(clickListener: (id: Int) -> Unit) {
        this.clickListener = clickListener
    }
}