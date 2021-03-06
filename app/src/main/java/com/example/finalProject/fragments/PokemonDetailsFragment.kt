package com.example.finalProject.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.example.finalProject.R
import com.example.finalProject.adapter.EvolutionAdapter
import com.example.finalProject.databinding.FragmentPokemonDetailsBinding
import com.example.finalProject.extensions.loadMaybeSvg
import com.example.finalProject.models.PokemonDetail
import com.example.finalProject.utils.CustomDividerItemDecoration
import com.example.finalProject.utils.PrefManager
import com.example.finalProject.utils.Utils
import com.example.finalProject.viewmodels.PokemonDetailsViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PokemonDetailsFragment : Fragment() {
    private val arguments: PokemonDetailsFragmentArgs by navArgs()
    private var _binding: FragmentPokemonDetailsBinding ? = null
    private val binding get() = _binding!!
    private val dispose = CompositeDisposable()
    private lateinit var evolutionAdapter: EvolutionAdapter
    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        evolutionAdapter = EvolutionAdapter()
        binding.evolutionChain.adapter = evolutionAdapter

        activity?.let {
            val divider = CustomDividerItemDecoration(it, DividerItemDecoration.HORIZONTAL)
            divider.setDrawable(ContextCompat.getDrawable(it, R.drawable.arrow)!!)
            binding.evolutionChain.addItemDecoration(divider)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = this.context?.let { PrefManager(it).name } ?: ""

        binding.textGreeting.text = "Hola $userName"

        Glide.with(this).load(R.drawable.loading).into(binding.imageLoading)

        toggleLoading(true)

        dispose.add((viewModel.makeAPIRequest(arguments.pokemonName)).subscribe({
            loadHeaderInfo(it)
            loadTypesInfo(it)
            loadStatsInfo(it, view)
            loadEvolutionChainInfo(it)
            toggleLoading(false)
        },{
            //TODO manejar error
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            findNavController().popBackStack()
        }))
    }

    private fun loadEvolutionChainInfo(details: PokemonDetail) {
        evolutionAdapter.pokemons = details.evolutions

        evolutionAdapter.onItemClick = {
            if (details.pokemon.name != it) {
                val action = PokemonDetailsFragmentDirections.actionPokemonDetailsFragmentSelf(it)
                findNavController().navigate(action)
            }
        }
    }

    private fun toggleLoading(show: Boolean) {
        binding.imageLoading.visibility = if (show) View.VISIBLE else View.GONE
        binding.mainContent.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun loadHeaderInfo(detail: PokemonDetail) {
        binding.pokemonName.text = detail.pokemon.name
        binding.pokemonDesc.text = detail.species.flavor_text_entries
            .find{ txt ->
                txt.language.name == "en"
            }
            ?.flavor_text?.replace("\n", " ")
        binding.pokemonImage.loadMaybeSvg(detail.pokemon.sprites.other.dream_world.front_default, detail.pokemon.sprites.front_default)
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