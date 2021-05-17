package com.example.finalProject.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.entities.User
import com.example.finalProject.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val getAllUser: LiveData<List<User>>
    private val repository: PokemonRepository
    private var user: User? = null

    init {
        val userDao = PokemonDatabase.getDatabase(application).UserDAO()
        repository = PokemonRepository(userDao)
        getAllUser = repository.getAllUser()
    }

    fun insertUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertUser(user)
        }
    }

    fun getAllUser() : LiveData<List<User>> = getAllUser

    fun getUserById(emailUser: String, passwordUser: String): User? {
        viewModelScope.launch(Dispatchers.IO) {
            user = repository.getUserById(emailUser, passwordUser)
        }
        return user
    }

}