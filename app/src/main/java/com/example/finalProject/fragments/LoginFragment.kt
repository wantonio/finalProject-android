package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalProject.R
import com.example.finalProject.databinding.FragmentLoginBinding
import com.example.finalProject.db.entities.User
import com.example.finalProject.viewmodels.UserViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction

class LoginFragment : Fragment(R.layout.fragment_login){


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val disposable = CompositeDisposable()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/5/51/Pokebola-pokeball-png-0.png").into(binding.ImageViewLogin)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        /*binding.btnLogin.setOnClickListener {
            readDataToDatabase()
        }*/

        disposable.add(
            Observable.combineLatest(binding.txtUser.textChanges(), binding.txtPassword.textChanges(),
                { queryText, quantity -> queryText.toString().isNotEmpty() && quantity.toString().isNotEmpty() })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.btnLogin.isEnabled = it
                }
        )

        disposable.add(
               binding.btnLogin.clicks()
                       .subscribe() {
                           findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                       }
        )
    }


}