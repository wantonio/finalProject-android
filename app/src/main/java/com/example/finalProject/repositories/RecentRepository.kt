package com.example.finalProject.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.dao.PokemonRecentDAO
import com.example.finalProject.db.dao.UserDAO
import com.example.finalProject.db.entities.PokemonRecent
import com.example.finalProject.db.entities.User

class PokemonRecentRepository(private val PokemonRecentDao: PokemonRecentDAO) {

    suspend fun insertPokemon(pokemon: PokemonRecent){
        PokemonRecentDao.insertPokemon(pokemon)
    }

    fun getAllPokemon(): LiveData<List<PokemonRecent>> = PokemonRecentDao.getAllPokemon()

   //fun getUserById(emailUser: String, passwordUser: String): User = PokemonRecentDao.getUserById(emailUser, passwordUser)

}