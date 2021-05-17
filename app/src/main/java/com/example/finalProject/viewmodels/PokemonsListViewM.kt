package com.example.finalProject.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.finalProject.api.APIServiceList
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.entities.Recent
import com.example.finalProject.models.PokemonListResponse
import com.example.finalProject.repositories.RecentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonesListViewM(application: Application) : AndroidViewModel(application) {
    private val pokemonList = MutableLiveData<PokemonListResponse>()
    private var service: APIServiceList
    private var repository: RecentRepository

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIServiceList::class.java)
        val recentDao = PokemonDatabase.getDatabase(application).RecentDAO()
        repository = RecentRepository(recentDao)
    }

    fun makeAPIRequest(){
        service.getAllPokemons(limit = 200)
            .enqueue(object : Callback<PokemonListResponse> {
                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                    response.body()?.let { response ->
                        getUserRecent{
                                recs ->
                            response.results.forEach{
                                    pokemon ->
                                pokemon.isRecent = recs.any{ r -> r.name == pokemon.name}
                            }
                            pokemonList.postValue(response)
                        }
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

    fun getUserRecent(cb: (list: List<Recent>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO){
            val recent = repository.getUserRecents(1)
            cb(recent)
        }
    }

    fun isRecent(userId: Int, pokemonName: String, cb: (fav: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO){
            val rec = repository.isPokemonRecent(userId, pokemonName).isNotEmpty()
            cb(rec)
        }
    }

    fun insertRecent(userId: Int, pokemonName: String, url: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertRecent(Recent(0, userId, pokemonName, url))
        }
    }

    fun deleteRecent(userId: Int, pokemonName: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteRecent(userId, pokemonName)
        }
    }



}