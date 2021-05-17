package com.example.finalProject.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.finalProject.api.APIServiceList
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.db.entities.User
import com.example.finalProject.db.models.UserFavorites
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.models.PokemonListResponse
import com.example.finalProject.repositories.FavoriteRepository
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonesListViewM(application: Application) : FavoritesViewModel(application) {
    private val pokemonList = MutableLiveData<PokemonListResponse>()
    private var service: APIServiceList
    private var repository: FavoriteRepository

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIServiceList::class.java)
        val favoriteDao = PokemonDatabase.getDatabase(application).FavoriteDAO()
        repository = FavoriteRepository(favoriteDao)
    }

    fun makeAPIRequest(){
        service.getAllPokemons(limit = 200)
            .enqueue(object : Callback<PokemonListResponse> {
                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                    response.body()?.let { response ->
                        pokemonList.postValue(response)
                    }
                }

                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    val a = ""
                }
            })
    }

    fun getPokemonList() : LiveData<PokemonListResponse>{
        return pokemonList
    }
}