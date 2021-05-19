package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.finalProject.adapter.PokemonAdapter
import com.example.finalProject.databinding.HomeRecentFragmentBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.viewmodels.PokemonesListViewM
import com.example.finalProject.viewmodels.RecentViewModel

class HomeRecentFragment : Fragment() {
    private var _binding: HomeRecentFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()
    private val viewModelFavs: PokemonesListViewM by viewModels()
    private val viewModel: RecentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeRecentFragmentBinding.inflate(inflater, container, false)
        binding.pokemonsRecyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        activity?.let {
            itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
        }
        binding.pokemonsRecyclerView.addItemDecoration(itemDecoration)

        adapter.setOnItemClickListener {
            val action = HomeRecentFragmentDirections.actionHomeRecentToPokemonDetailsFragment(it)
            findNavController().navigate(action)
        }



        adapter.addRecent = {
                pokemon, pos, shouldAdd ->
            if (shouldAdd) {
                viewModel.insertRecent(pokemon.name, pokemon.url)
            } else {
                //adapter.pokemons.removeAt(pos)
                viewModel.deleteRecent(pokemon.name)
                adapter.notifyDataSetChanged()
                toggleEmptyView(adapter.pokemons.isEmpty())
            }
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        adapter.addFavorite = {
                pokemon, pos, shouldAdd ->
            if (shouldAdd) {
                viewModelFavs.insertFavorite(pokemon.name, pokemon.url)
            } else {
                viewModelFavs.deleteFavorite(pokemon.name)
            }
        }


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getUserRecent().observe(viewLifecycleOwner) {
            toggleEmptyView(it.isEmpty())

            val items = it.map{
                PokemonListItem(it.name, it.url, true)
            }

            // adapter.pokemons = it as MutableList<PokemonListItem>
            adapter.pokemons = items as MutableList<PokemonListItem>
        }

        viewModelFavs.getPokemonList().observe(viewLifecycleOwner) { pokemons ->
            viewModelFavs.getUserFavorites().observe(viewLifecycleOwner) { favs ->
                toggleEmptyView(pokemons.results.isEmpty())
                var list = pokemons.results.map { pokemon ->
                    pokemon.isFavorite = favs.any { f -> f.name == pokemon.name }
                    pokemon
                }



                adapter.totalCount = pokemons.count
                adapter.pokemons = list as MutableList<PokemonListItem>
            }
        }

    }




    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonsRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }
}