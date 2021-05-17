package com.example.finalProject.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.entities.Recent
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.repositories.RecentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel

class RecentsListViewModel(application: Application) : AndroidViewModel(application) {

    private val recentsList = MutableLiveData<List<PokemonListItem>>()
    private var repository: RecentRepository

    init {
        val recentDao = PokemonDatabase.getDatabase(application).RecentDAO()
        repository = RecentRepository(recentDao)
    }

    fun getUserRecents():MutableLiveData<List<PokemonListItem>>  {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getUserRecents(1).map{
                PokemonListItem(it.name, it.url, true)
            }
            recentsList.postValue(items)
        }

        return recentsList
    }

    fun isRecent(userId: Int, pokemonName: String, cb: (fav: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val fav = repository.isPokemonRecent(userId, pokemonName).isNotEmpty()
            cb(fav)
        }
    }

    fun insertRecent(userId: Int, pokemonName: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecent(Recent(0, userId, pokemonName, url))
        }
    }

    fun deleteRecent(userId: Int, pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecent(userId, pokemonName)
        }
    }

}