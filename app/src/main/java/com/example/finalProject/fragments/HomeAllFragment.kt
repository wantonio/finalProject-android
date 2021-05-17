package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.finalProject.db.entities.User

class HomeAllFragment : Fragment(R.layout.home_all_fragment) {
    private var _binding: HomeAllFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = PokemonAdapter()
    private val viewModel: PokemonesListViewM by viewModels()

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

    }

    private fun toggleEmptyView(show: Boolean) {
        binding.emptyListAll.visibility = if (show) View.VISIBLE else View.GONE
        binding.pokemonListAllRecyclerView.visibility = if (show) View.GONE else View.VISIBLE
    }


  /*  private fun insertDataToDatabase() {
        val pokemonName = binding.TxtFragName.text.toString()
        val pokemonName1 = binding.
        TxtFragUser.text.toString()
        val image = binding.imageView.text.toString()


        val user = User(0,userName,name,email,password)
        mUserViewModel.insertUser(user)
        Toast.makeText(requireContext(), "Usuario creado", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    } */

}