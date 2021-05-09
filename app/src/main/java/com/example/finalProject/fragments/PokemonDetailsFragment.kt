package com.example.finalProject.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.finalProject.R
import com.example.finalProject.databinding.FragmentPokemonDetailsBinding
import com.example.finalProject.extensions.loadSvg
import com.example.finalProject.models.PokemonDetail
import com.example.finalProject.utils.Utils
import com.example.finalProject.viewmodels.PokemonDetailsViewModel
import com.example.finalProject.views.PokemonEvolution
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PokemonDetailsFragment : Fragment() {
    private val arguments: PokemonDetailsFragmentArgs by navArgs()
    private var _binding: FragmentPokemonDetailsBinding ? = null
    private val binding get() = _binding!!
    private val dispose = CompositeDisposable()

    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(R.drawable.loading).into(binding.imageLoading)

        binding.evolution1.setOnClickListener{
            handleEvolutionClick(0)
        }

        binding.evolution2.setOnClickListener{
            handleEvolutionClick(1)
        }

        binding.evolution3.setOnClickListener{
            handleEvolutionClick(2)
        }

        viewModel.getPokemonDetail().observe(viewLifecycleOwner) {
            loadHeaderInfo(it)
            loadTypesInfo(it)
            loadStatsInfo(it, view)
            loadEvolutionsInfo(it, view)
            toggleLoading(false)
        }

        val name = if (arguments.pokemonName.isNullOrEmpty()) "venusaur" else arguments.pokemonName

        dispose.add((viewModel.makeAPIRequest(name)).subscribe({},{
            //TODO manejar error
        }))
    }

    private fun toggleLoading(show: Boolean) {
        binding.imageLoading.visibility = if (show) View.VISIBLE else View.GONE
        binding.mainContent.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun handleEvolutionClick(index: Int) {
        val details = viewModel.getPokemonDetail().value
        val evolution = details?.evolutions?.get(index)
        val pokemon = details?.pokemon

        if (pokemon != null && evolution != null && pokemon.name != evolution.name) {
            toggleLoading(true)
            val action = PokemonDetailsFragmentDirections.actionPokemonDetailsFragmentSelf(evolution.name)
            findNavController().navigate(action)
        }
    }

    private fun loadHeaderInfo(detail: PokemonDetail) {
        binding.pokemonName.text = detail.pokemon.name
        binding.pokemonDesc.text = detail.species.flavor_text_entries
            .find{ txt ->
                txt.language.name == "en"
            }
            ?.flavor_text?.replace("\n", " ")
        binding.pokemonImage.loadSvg(detail.pokemon.sprites.other.dream_world.front_default )
    }

    private fun loadTypesInfo(detail: PokemonDetail) {
        val chipLayoutParams = ChipGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        chipLayoutParams.setMargins(10, 0, 10, 0)

        detail.pokemon.types.forEach { innerObj ->
            val chip = Chip(this.context)
            chip.text = innerObj.type.name
            chip.layoutParams = chipLayoutParams
            binding.wrapType.addView(chip)
        }
    }

    private fun loadStatsInfo(details: PokemonDetail, view: View) {
        setStat(binding.textHeight, details.pokemon.height.toString())
        setStat(binding.textWeight, details.pokemon.weight.toString())

        details.pokemon.stats.forEach{
            val id = Utils.getResId("textStat_${it.stat.name.replace("-","_")}", R.id::class.java)

            if(id != -1) {
                val textView: TextView = view.findViewById(id)
                setStat(textView, it.base_stat.toString())
            }
        }
    }

    private fun loadEvolutionsInfo(details: PokemonDetail, view: View) {
        details.evolutions.forEachIndexed{
                index, pokemon ->

            val id = Utils.getResId("evolution_${index + 1}", R.id::class.java)
            var arrow: TextView? = null

            if (index == 1) {
                arrow = binding.evolutionArrow1
            } else if(index == 2) {
                arrow = binding.evolutionArrow2
            }

            if(id != -1) {
                val evolution: PokemonEvolution = view.findViewById(id)

                evolution.setName(pokemon.name)
                evolution.getViewImage().loadSvg(pokemon.sprites.other.dream_world.front_default)
                evolution.visibility = View.VISIBLE

                if(arrow != null) {
                    arrow.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setStat(statText: TextView, value: String) {
        val text:String = getColoredStat( statText.text.toString(), value )
        statText.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    }

    private fun getColoredStat(stat: String, value: String): String {
        val color:String = context?.let { ContextCompat.getColor(it, R.color.red) }.toString()

        return "<font color=$color>$stat:</font> $value"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dispose.clear()
        _binding = null
    }
}