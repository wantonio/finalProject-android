package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalProject.databinding.HomeAllFragmentBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
<<<<<<< Updated upstream
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalProject.R
import com.example.finalProject.adapter.ListAllAdapter
import com.example.finalProject.viewmodels.PokemonsListViewM

class HomeAllFragment : Fragment(R.layout.home_all_fragment) {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = ListAllAdapter()



            private val viewModel: PokemonsListViewM by viewModels()
=======
import com.example.finalProject.R
import com.example.finalProject.adapter.ListAllAdapter
import com.example.finalProject.viewmodels.PokemonesListViewM

class HomeAllFragment : Fragment(R.layout.home_all_fragment) {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding : HomeAllFragmentBinding get() = _binding!!
    private val adapter = ListAllAdapter()

        private val viewModel: PokemonesListViewM by viewModels()
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.makeAPIRequest()
    }

    override fun onCreateView(
<<<<<<< Updated upstream
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
=======
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
>>>>>>> Stashed changes
    ): View? {
        _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonListAllRecyclerView.adapter

        viewModel.getPokemonList().observe(viewLifecycleOwner) { it
            adapter.pokemonesLista = it

        }

    }


<<<<<<< Updated upstream
    /* override fun onCreateView(
=======

     /* override fun onCreateView(
>>>>>>> Stashed changes
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeAllFragmentBinding.inflate(inflater, container, false)
        binding.pokemonListAllRecyclerView.adapter = adapter
        adapter.pokemons = getDummyPokemons()


         val itemDecoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
         activity?.let {
             itemDecoration.setDrawable(ContextCompat.getDrawable(it, R.drawable.divider)!!)
         }
         binding.pokemonListAllRecyclerView.addItemDecoration(itemDecoration)

         adapter.setOnItemClickListener {
             val action = HomeAllFragmentDirections.actionHomeAllToPokemonDetailsFragment(it)
             findNavController().navigate(action)
         }

         toggleEmptyView(adapter.pokemons.isEmpty())

        return binding.root
    }

    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonListAllRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    } */


  /*  private fun getDummyPokemons() : List<Contact> {
        return mutableListOf(
            Contact("Pokemon 1", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c325.png"),
            Contact("Pokemon 2", "https://assets.stickpng.com/thumbs/580b57fcd9996e24bc43c32a.png"),
            Contact("Pokemon 3", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c31a.png"),
            Contact("Pokemon 4", "https://pm1.narvii.com/6270/a66f81df957afcda3bbb6b1723b5b3928436a8dc_hq.jpg"),
            Contact("Pokemon 5", "https://i.pinimg.com/originals/4d/88/eb/4d88ebc2b8b3a26b8d698ff189f340b3.png"),
        )
    } */

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