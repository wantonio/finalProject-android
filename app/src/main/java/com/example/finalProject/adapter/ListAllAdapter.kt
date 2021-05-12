package com.example.finalProject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalProject.databinding.ActivityMainBinding
import com.example.finalProject.databinding.HomeAllFragmentBinding
import com.example.finalProject.databinding.ListCellBinding
import com.example.finalProject.models.PokemonList
import com.example.finalProject.models.PokemonListDetail


class ListAllAdapter : RecyclerView.Adapter<ListAllAdapter.PokemonViewHolder>(){
    // private var _binding: ListCellBinding? = null
    // private val binding get() = _binding!!


    var pokemonesLista: List<PokemonListDetail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class PokemonViewHolder(private val binding: ListCellBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(pokemon: PokemonList){
            binding.namePokemon.text = pokemon.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = ListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }



    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonesLista[position].PokemonList.first())
    }

    override fun getItemCount(): Int = pokemonesLista.size

}

