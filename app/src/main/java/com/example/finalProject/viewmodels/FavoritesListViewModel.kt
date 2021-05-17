package com.example.finalProject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.finalProject.api.APIServiceList
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.models.PokemonListResponse
import com.example.finalProject.repositories.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoritesListViewModel(application: Application) : AndroidViewModel(application) {
    private val favoritesList = MutableLiveData<List<PokemonListItem>>()
    private var repository: FavoriteRepository

    init {
        val favoriteDao = PokemonDatabase.getDatabase(application).FavoriteDAO()
        repository = FavoriteRepository(favoriteDao)
    }

    fun getUserFavorites():MutableLiveData<List<PokemonListItem>>  {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getUserFavorites(1).map{
                PokemonListItem(it.name, it.url, true)
            }
            favoritesList.postValue(items)
        }

        return favoritesList
    }

    fun isFavorite(userId: Int, pokemonName: String, cb: (fav: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val fav = repository.isPokemonFavorite(userId, pokemonName).isNotEmpty()
            cb(fav)
        }
    }

    fun insertFavorite(userId: Int, pokemonName: String, url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(Favorite(0, userId, pokemonName, url))
        }
    }

    fun deleteFavorite(userId: Int, pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(userId, pokemonName)
        }
    }
}