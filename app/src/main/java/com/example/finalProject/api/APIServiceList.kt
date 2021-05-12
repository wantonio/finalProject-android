package com.example.finalProject.api

import com.example.finalProject.models.PokemonListItem
import com.example.finalProject.models.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServiceList {
    @GET("api/v2/pokemon/")
    fun getAllPokemons(

            @Query("limit") limit: Int


    ): Call<PokemonListResponse>

    @GET("api/v2/pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int):Call<PokemonListItem>

}