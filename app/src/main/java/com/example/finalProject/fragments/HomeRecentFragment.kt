package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.finalProject.adapter.PokemonAdapter
import com.example.finalProject.databinding.HomeRecentFragmentBinding
import com.example.finalProject.models.Contact
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.viewmodels.RecentsListViewModel

class HomeRecentFragment : Fragment() {
    private var _binding: HomeRecentFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()
    private val viewModel: RecentsListViewModel by viewModels()

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
            val action = HomeFavoritesFragmentDirections.actionHomeFavoritesToPokemonDetailsFragment(it)
            findNavController().navigate(action)
        }


        adapter.addRecent = {
                pokemon, pos, shouldAdd ->
            if (shouldAdd) {
                viewModel.insertRecent(1, pokemon.name, pokemon.url)
            } else {
                //adapter.pokemons.removeAt(pos)
                viewModel.deleteRecent(1, pokemon.name)
                adapter.notifyDataSetChanged()
                toggleEmptyView(adapter.pokemons.isEmpty())
            }
        }

        return binding.root
    }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            viewModel.getUserRecents().observe(viewLifecycleOwner) {
                toggleEmptyView(it.isEmpty())
                adapter.pokemons = it as MutableList<PokemonListItem>
            }
        }




    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonsRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }
}