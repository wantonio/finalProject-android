package com.example.finalProject.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalProject.R
import com.example.finalProject.databinding.FragmentLoginBinding
import com.example.finalProject.databinding.FragmentRegisterBinding
import com.example.finalProject.db.entities.User
import com.example.finalProject.viewmodels.PokemonListViewModel
import com.example.finalProject.viewmodels.UserViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val disposable = CompositeDisposable()

    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnFragRegister.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable.add(
                Observable.combineLatest(binding.TxtFragUser.textChanges(), binding.TxtFragName.textChanges(),
                        binding.TxtFragEmail.textChanges(), binding.TxtFragPassword.textChanges(),
                        { UserText, NameText, EmailText, PasswordText ->
                            UserText.toString().isNotEmpty() && NameText.toString().isNotEmpty() &&
                                    EmailText.toString().isNotEmpty() && PasswordText.toString().isNotEmpty()
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            binding.btnFragRegister.isEnabled = it
                        }
        )
    }

    private fun insertDataToDatabase() {
        val userName = binding.TxtFragUser.text.toString()
        val name = binding.TxtFragName.text.toString()
        val email = binding.TxtFragEmail.text.toString()
        val password = binding.TxtFragPassword.text.toString()

        val user = User(0,userName,name,email,password)
        mUserViewModel.insertUser(user)
        Toast.makeText(requireContext(), "Usuario creado", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }
}
