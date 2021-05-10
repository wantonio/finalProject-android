package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.finalProject.adapter.PokemonAdapter
import com.example.finalProject.databinding.HomeFavoritesFragmentBinding
import com.example.finalProject.models.Contact
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R

class HomeFavoritesFragment : Fragment() {
    private var _binding: HomeFavoritesFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFavoritesFragmentBinding.inflate(inflater, container, false)
        binding.pokemonsRecyclerView.adapter = adapter
        adapter.pokemons = getDummyPokemons()

        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        activity?.let {
            itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
        }
        binding.pokemonsRecyclerView.addItemDecoration(itemDecoration)

        adapter.setOnItemClickListener {
            val action = HomeFavoritesFragmentDirections.actionHomeFavoritesToPokemonDetailsFragment(0)
            findNavController().navigate(action)
        }

        toggleEmptyView(adapter.pokemons.isEmpty())

        return binding.root
    }

    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonsRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun getDummyPokemons() : List<Contact> {
        return mutableListOf()
    }
}