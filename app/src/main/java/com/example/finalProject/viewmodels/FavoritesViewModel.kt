package com.example.finalProject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.repositories.FavoriteRepository
import com.example.finalProject.utils.PrefManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class FavoritesViewModel(application: Application): AndroidViewModel(application) {
    private var repository: FavoriteRepository
    private var userId: Int? = null

    init {
        val favoriteDao = PokemonDatabase.getDatabase(application).FavoriteDAO()
        userId = PrefManager(application.applicationContext).userId
        repository = FavoriteRepository(favoriteDao)
    }

    fun getUserFavorites(): LiveData<List<Favorite>> {
        return repository.getUserFavoritesTest(userId ?: 0 )
    }

    fun insertFavorite(pokemonName: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userId?.let{
                repository.insertFavorite(Favorite(0, it, pokemonName, url))
            }
        }
    }

    fun deleteFavorite(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userId?.let {
                repository.deleteFavorite(it, pokemonName)
            }
        }
    }
}