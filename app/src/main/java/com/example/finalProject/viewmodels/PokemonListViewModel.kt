package com.example.finalProject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalProject.api.APIService
import com.example.finalProject.models.Pokemon
import com.example.finalProject.models.PokemonDetail
import com.example.finalProject.models.PokemonResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonListViewModel() : ViewModel() {

    private val pokemonList = MutableLiveData<List<PokemonDetail>>()
    private var service: APIService
    private var pokemon = MutableLiveData<Pokemon>()


    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIService::class.java)
    }

    fun makeAPIRequest(){
        service.getAllPokemons(limit = 1500)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    response.body()?.let {
                        pokemonList.postValue(it.results)
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    val a = ""
                }

            })
    }


   fun makeAPIRequestById(id: Int) {
        service.getPokemonById(id)
            .enqueue(object : Callback<Pokemon> {
                override fun onResponse(
                    call: Call<Pokemon>,
                    response: Response<Pokemon>
                ) {
                    response.body()?.let {
                        pokemon.postValue(it)
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    val a = ""
                }

            })
    }


    /*fun getPokemonList(): LiveData<List<PokemonDetail>> {
        return pokemonList
    }*/

}

