package com.example.finalProject.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.finalProject.databinding.PokemonEvolutionBinding


class PokemonEvolution: ConstraintLayout {
    private val binding = PokemonEvolutionBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {

        // get the inflater service from the android system

        // val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


        // _binding = PokemonEvolutionBinding.inflate(LayoutInflater.from(context), this, false)
        // inflate the layout into "this" layout
        // inflater.inflate(R.layout.pokemon_evolution, this)
    }

    fun getViewImage(): ImageView {
        return binding.evolutionImage
    }

    fun setName(name: String) {
        binding.evolutionName.text = name
    }
}