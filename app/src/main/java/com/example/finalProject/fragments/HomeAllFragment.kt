package com.example.finalProject.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.finalProject.adapter.PokemonAdapter
import com.example.finalProject.databinding.HomeAllFragmentBinding
import com.example.finalProject.models.Contact
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R
import com.example.finalProject.models.PokemonList

class HomeAllFragment : Fragment() {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()

    /*override fun onCreateView(
       inflater: LayoutInflater,
       container: ViewGroup?,
       savedInstanceState: Bundle?
   ): View? {
       _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
       binding.pokemonsRecyclerView.adapter = adapter */
    //adapter.contacts = getDummyPokemons()


    /* val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
     activity?.let {
         itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
     }
     binding.pokemonsRecyclerView.addItemDecoration(itemDecoration)

     adapter.setOnItemClickListener {
         val action = HomeAllFragmentDirections.actionHomeAllToPokemonDetailsFragment(it)
         findNavController().navigate(action)
     }

    return binding.root
} */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonsRecyclerView.adapter = adapter

        adapter.pokemons = getDummyPokemons()
    }

    private fun getDummyPokemons() : List<PokemonList>{
        return listOf(
            PokemonList(1, "poke 1"),
            PokemonList(2, "poke 2"),
            PokemonList(3, "poke 13")

        )

    }


    /* private fun getDummyPokemons() : List<Contact> {
        return mutableListOf(
            Contact("Pokemon 1", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png"),
            Contact("Pokemon 2", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png"),
            Contact("Pokemon 3", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png"),
            Contact("Pokemon 4", "https://pm1.narvii.com/6270/a66f81df957afcda3bbb6b1723b5b3928436a8dc_hq.jpg"),
            Contact("Pokemon 5", "https://i.pinimg.com/originals/4d/88/eb/4d88ebc2b8b3a26b8d698ff189f340b3.png"),
        )
    } */

    /* private fun initStats() {
        for(child in binding.wrapStats.children) {
            val auxTextView: TextView = (child as TextView)
            val text:String = getColoredStat( auxTextView.text.toString(), "76" )
            auxTextView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }
    } */




}