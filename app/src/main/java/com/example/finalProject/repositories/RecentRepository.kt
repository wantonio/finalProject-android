package com.example.finalProject.repositories

import androidx.lifecycle.LiveData
import com.example.finalProject.db.dao.RecentDAO
import com.example.finalProject.db.entities.Recent

class RecentRepository(private val recentDAO: RecentDAO) {

    suspend fun getUserRecents(userId: Int): List<Recent> = recentDAO.getUserRecents(userId)

    suspend fun isPokemonRecent(userId: Int, pokemonName: String): List<Int>{
        return recentDAO.isPokemonRecent(
            userId,
            pokemonName
        )
    }

    suspend fun insertRecent(favorite: Recent){
        recentDAO.insertRecent(favorite)
    }

    suspend fun deleteRecent(userId: Int, pokemonName: String){
        return recentDAO.deleteRecent(
            userId,
            pokemonName
        )
    }

}