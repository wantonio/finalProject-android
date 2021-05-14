package com.example.finalProject.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
import com.google.android.material.textfield.TextInputEditText
import com.jakewharton.rxbinding4.InitialValueObservable
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChangeEvents
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable.combineLatest
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.internal.threadName
import java.util.concurrent.TimeUnit


 class HomeAllFragment : Fragment(R.layout.home_all_fragment) {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()
    private val viewModel: PokemonesListViewM by viewModels()

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         adapter.setOnItemClickListener {
             val action = HomeAllFragmentDirections.actionHomeAllToPokemonDetailsFragment(it)
             findNavController().navigate(action)
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
        disposable.clear()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pokemonListAllRecyclerView.adapter = adapter

        viewModel.getPokemonList().observe(viewLifecycleOwner) {
            toggleEmptyView(it.results.isEmpty())

            adapter.totalCount = it.count
            adapter.pokemons = it.results

        }



        disposable.add(
            binding.searchButton.clicks()
                .subscribe {
                    searchPokemon()
                }
        )




    }

     private fun searchPokemon() {

         disposable.add(

             binding.SearchBoxAll.textChanges()

                 .map { it.toString()}
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe {
                     val action = HomeAllFragmentDirections.actionHomeAllToPokemonDetailsFragment(it)
                     findNavController().navigate(action)


                 }
         )
     }


    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonListAllRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }






}



