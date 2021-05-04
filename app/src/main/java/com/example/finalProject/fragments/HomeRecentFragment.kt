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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R

class HomeRecentFragment : Fragment() {
    private var _binding: HomeRecentFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeRecentFragmentBinding.inflate(inflater, container, false)
        binding.pokemonsRecyclerView.adapter = adapter
        adapter.contacts = getDummyPokemons()

        val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        activity?.let {
            itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
        }
        binding.pokemonsRecyclerView.addItemDecoration(itemDecoration)

        adapter.setOnItemClickListener {
            val action = HomeRecentFragmentDirections.actionHomeRecentToPokemonDetailsFragment(0)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun getDummyPokemons() : List<Contact> {
        return mutableListOf(
            Contact("Pokemon reciente 1", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png"),
            Contact("Pokemon reciente 2", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png"),
            Contact("Pokemon reciente 3", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png"),
            Contact("Pokemon reciente 4", "https://pm1.narvii.com/6270/a66f81df957afcda3bbb6b1723b5b3928436a8dc_hq.jpg"),
            Contact("Pokemon reciente 5", "https://i.pinimg.com/originals/4d/88/eb/4d88ebc2b8b3a26b8d698ff189f340b3.png")
        )
    }
}