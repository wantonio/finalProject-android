package com.example.finalProject.api

import com.example.finalProject.models.Pokemon
import com.example.finalProject.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface APIService {

    @GET("api/v2/pokemon/")
    fun getAllPokemons(
            @Query("limit") limit: Int
    ):Call<PokemonResponse>

    @GET("api/v2/pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int):Call<Pokemon>


}