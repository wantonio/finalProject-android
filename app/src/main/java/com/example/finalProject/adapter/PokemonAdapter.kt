package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.models.Contact
import com.example.finalProject.databinding.ListCellBinding
import com.squareup.picasso.Picasso

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private var _binding: ListCellBinding? = null
    private val binding get() = _binding!!
    lateinit var clickListener: (pokemonId: Int) -> Unit
    var onEmptyList: (() -> Unit)? = null

    var pokemons: List<Contact> = emptyList()
        set(value) {
            field = value

            if (field.isEmpty()) {
                onEmptyList?.invoke()
            }

            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String, imageUrl: String) {
            binding.textView.text = name
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
        val contact = pokemons[position]
        holder.bind(contact.name, contact.imageURL)
    }

    override fun getItemCount(): Int = pokemons.size

    fun setOnItemClickListener(clickListener: (id: Int) -> Unit) {
        this.clickListener = clickListener
    }
}