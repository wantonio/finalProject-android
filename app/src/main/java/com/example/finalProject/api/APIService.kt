package com.example.finalProject.api

import com.example.finalProject.models.Evolution
import com.example.finalProject.models.Pokemon
import com.example.finalProject.models.PokemonSpecies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface APIService {
    @GET("pokemon/{name}")
    fun getPokemonByName(
        @Path("name") name: String
    ): Call<Pokemon>

    @GET()
    fun getPokemonSpeciesByUrl(
        @Url url: String
    ): Call<PokemonSpecies>

    @GET()
    fun getEvolutionByUrl(
        @Url url: String
    ): Call<Evolution>
}