package com.example.finalProject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalProject.api.APIServiceList
import com.example.finalProject.models.Pokemon
import com.example.finalProject.models.PokemonList
import com.example.finalProject.models.PokemonListDetail
import com.example.finalProject.models.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonesListViewM : ViewModel(){



    private val PokemonList = MutableLiveData<List<PokemonListDetail>>()
    private var service: APIServiceList

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIServiceList::class.java)
    }

    fun makeAPIRequest(){
        service.getAllPokemons(limit = 15)
            .enqueue(object : Callback<PokemonListResponse> {
                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                    response.body()?.let {
                        PokemonList.postValue(it.list)
                    }
                }

                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    val a = ""
                }

            })

    }

    fun getPokemonList() : LiveData<List<PokemonListDetail>>{
        return PokemonList

    }





}