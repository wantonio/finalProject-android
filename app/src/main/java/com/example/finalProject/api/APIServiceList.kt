package com.example.finalProject.api

import com.example.finalProject.models.Pokemon
import com.example.finalProject.models.PokemonList
import com.example.finalProject.models.PokemonListResponse
import com.example.finalProject.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServiceList {
    @GET("api/v2/pokemon/")
    fun getAllPokemons(
<<<<<<< Updated upstream
            @Query("limit") limit: Int
=======
         @Query("limit") limit: Int
>>>>>>> Stashed changes
    ): Call<PokemonListResponse>

    @GET("api/v2/pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int):Call<PokemonList>

}