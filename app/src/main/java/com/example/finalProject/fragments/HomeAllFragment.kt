package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.finalProject.adapter.PokemonAdapter
import com.example.finalProject.databinding.HomeAllFragmentBinding
import com.example.finalProject.models.Contact
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R

class HomeAllFragment : Fragment() {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()

     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
        binding.pokemonsRecyclerView.adapter = adapter
        adapter.pokemons = getDummyPokemons()


         val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
         activity?.let {
             itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
         }
         binding.pokemonsRecyclerView.addItemDecoration(itemDecoration)

         adapter.setOnItemClickListener {
             val action = HomeAllFragmentDirections.actionHomeAllToPokemonDetailsFragment("")
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
        return mutableListOf(
            Contact("Pokemon 1", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png"),
            Contact("Pokemon 2", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png"),
            Contact("Pokemon 3", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png"),
            Contact("Pokemon 4", "https://pm1.narvii.com/6270/a66f81df957afcda3bbb6b1723b5b3928436a8dc_hq.jpg"),
            Contact("Pokemon 5", "https://i.pinimg.com/originals/4d/88/eb/4d88ebc2b8b3a26b8d698ff189f340b3.png"),
        )
    }

        //busquedas

    //FORMA RX - REACTIVA
    /* disposable.add(
     binding.searchBox.textChanges()
     .skipInitialValue()
     .debounce(300, TimeUnit.MILLISECONDS)
     .map { it.toString() }
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe {
         binding.textInputLayout.error = if (it.isEmpty()) "Campo requerido" else null
     }
     )

     disposable.add(
     Observable.combineLatest(binding.searchBox.textChanges(), binding.quantityBox.textChanges(),
     { queryText, quantity -> queryText.toString().isNotEmpty() && quantity.toString().isNotEmpty() })
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe {
         binding.searchButton.isEnabled = it
     }
     )

     disposable.add(
     //binding.searchButton.clicks()
     .subscribe { viewModel.makeAPIRequest(binding.searchBox.text.toString(), binding.quantityBox.text.toString().toInt()) }
     )

     disposable.add(
     adapter.onItemClicked
     .throttleFirst(400, TimeUnit.MILLISECONDS)
     .subscribe {
         Log.d("TEST", "Item clicked: ${it.main}")
     }
     ) */


}