package com.example.finalProject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalProject.R
import com.example.finalProject.databinding.FragmentLoginBinding
import com.example.finalProject.databinding.FragmentRegisterBinding
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable.add(
            Observable.combineLatest(binding.TxtFragUser.textChanges(), binding.TxtFragName.textChanges(),
                                     binding.TxtFragEmail.textChanges(), binding.TxtFragPassword.textChanges(),
                { UserText, NameText, EmailText, PasswordText -> UserText.toString().isNotEmpty() && NameText.toString().isNotEmpty() &&
                                                                 EmailText.toString().isNotEmpty() && PasswordText.toString().isNotEmpty()})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding.btnFragRegister.isEnabled = it
                }
        )
    }

}
