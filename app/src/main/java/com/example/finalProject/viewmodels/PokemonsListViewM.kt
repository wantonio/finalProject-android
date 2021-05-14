package com.example.finalProject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalProject.api.APIServiceList
import com.example.finalProject.models.Pokemon
import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.models.PokemonListResponse
import com.example.finalProject.models.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PokemonesListViewM : ViewModel(){
    private val pokemonList = MutableLiveData<PokemonListResponse>()
    private var service: APIServiceList

    init {
        val retrofit = Retrofit.Builder()

            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIServiceList::class.java)

    }

    fun makeAPIRequest(){
        service.getAllPokemons(limit = 200)
            .enqueue(object : Callback<PokemonListResponse> {
                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                    response.body()?.let {
                        pokemonList.postValue(it)
                    }
                }

                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    val a = ""
                }

            })

    }

     fun getPokemon(name: String): Observable<PokemonListResponse> {
        return Observable.create { emitter ->
            service.getPokemonByName(name)
                .enqueue(object : Callback<PokemonListResponse> {
                    override fun onResponse(
                        call: Call<PokemonListResponse>,
                        response: Response<PokemonListResponse>
                    ) {
                        val body = response.body()

                        if (body != null) {
                            emitter.onNext(body)
                            emitter.onComplete()
                        } else {
                            emitter.onError(Throwable("not found"))
                        }
                    }

                    override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                        emitter.onError(t)
                    }

                })
        }
    }

    fun makeAPIRequestSearch(name: String){
        service.se(name)
            .enqueue(object : Callback<PokemonListResponse> {
                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                    response.body()?.let {
                        pokemonList.postValue(it)
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