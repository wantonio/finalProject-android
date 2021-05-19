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
import com.example.finalProject.utils.PrefManager

open class RecentViewModel(application: Application) : AndroidViewModel(application) {


    private var repository: RecentRepository
    private var userId: Int? = null

    init {
        val recentDao = PokemonDatabase.getDatabase(application).RecentDAO()
        userId = PrefManager(application.applicationContext).userId
        repository = RecentRepository(recentDao)
    }

    fun getUserRecent(): LiveData<List<Recent>>  {
        return repository.getUserRecentAl(userId ?: 0 )
    }




    fun insertRecent(pokemonName: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userId?.let{
                repository.insertRecent(Recent(0, it, pokemonName, url))
            }
        }
    }


    fun deleteRecent(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userId?.let {
                repository.deleteRecent(it, pokemonName)
            }
        }
    }

}