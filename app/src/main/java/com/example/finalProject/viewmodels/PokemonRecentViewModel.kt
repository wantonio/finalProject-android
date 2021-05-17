package com.example.finalProject.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.finalProject.db.PokemonRecentDataBase
import com.example.finalProject.db.entities.PokemonRecent
import com.example.finalProject.db.entities.User
import com.example.finalProject.repositories.PokemonRecentRepository
import com.example.finalProject.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonRecentViewModel(application: Application) : AndroidViewModel(application) {


     //val getAllPokemon LiveData<List<PokemonRecent>>
    private val repository: PokemonRecentRepository
    private var pokemon: PokemonRecent? = null

    init {
        val PokemonRecentDAO = PokemonRecentDataBase.getDatabase(application).PokemonRecentDAO()
        repository = PokemonRecentRepository(PokemonRecentDAO)
        //getAllPokemon = repository.getAllPokemon()
    }

    fun insertPokemon(pokemon: PokemonRecent){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertPokemon(pokemon)
        }
    }

    fun getAllPokemon() : LiveData<List<PokemonRecent>> = getAllPokemon

    /*fun getUserById(emailUser: String, passwordUser: String): User? {
        viewModelScope.launch(Dispatchers.IO) {
            user = repository.getUserById(emailUser, passwordUser)
        }
        return user
    } */

}