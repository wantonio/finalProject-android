package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.finalProject.databinding.HomeAllFragmentBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.finalProject.R
import com.example.finalProject.adapter.PokemonAdapter
import com.example.finalProject.viewmodels.PokemonesListViewM
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.utils.PrefManager
import com.example.finalProject.viewmodels.PokemonesListViewModelRecent

class HomeAllFragment : Fragment(R.layout.home_all_fragment) {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()
    private val viewModel: PokemonesListViewM by viewModels()
    private val viewModelRecent: PokemonesListViewModelRecent by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         adapter.setOnItemClickListener {
             val action = HomeAllFragmentDirections.actionHomeAllToPokemonDetailsFragment(it)
             findNavController().navigate(action)
         }

        adapter.addRecent = {
                pokemon, pos, shouldAdd ->
            if (shouldAdd ) {

                viewModelRecent.insertRecent(pokemon.name, pokemon.url)
            }
        }

        adapter.addFavorite = {
            pokemon, pos, shouldAdd ->
            if (shouldAdd) {
                viewModel.insertFavorite(pokemon.name, pokemon.url)
            } else {
                viewModel.deleteFavorite(pokemon.name)
            }
        }

        viewModel.makeAPIRequest()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
        activity?.let {
            val itemDecoration = DividerItemDecoration(it, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
            binding.pokemonListAllRecyclerView.addItemDecoration(itemDecoration)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonListAllRecyclerView.adapter = adapter

        viewModel.getPokemonList().observe(viewLifecycleOwner) { pokemons ->
            viewModel.getUserFavorites().observe(viewLifecycleOwner) { favs ->
                toggleEmptyView(pokemons.results.isEmpty())
                var list = pokemons.results.map{
                        pokemon ->
                        pokemon.isFavorite = favs.any{ f -> f.name == pokemon.name}
                        pokemon
                }



                adapter.totalCount = pokemons.count
                adapter.pokemons = list as MutableList<PokemonListItem>
            }

            viewModelRecent.getUserRecent().observe(viewLifecycleOwner) { rec ->
                toggleEmptyView(pokemons.results.isEmpty())
                var list = pokemons.results.map {
                        pokemon ->
                    pokemon.isRecent = rec.any { r -> r.name == pokemon.name }
                    pokemon
                }

                adapter.totalCount = pokemons.count
                adapter.pokemons = list as MutableList<PokemonListItem>
            }

        }
    }

    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonListAllRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }


}