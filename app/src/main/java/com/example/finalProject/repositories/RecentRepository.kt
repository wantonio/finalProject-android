package com.example.finalProject.repositories

import androidx.lifecycle.LiveData
import com.example.finalProject.db.dao.RecentDAO
import com.example.finalProject.db.entities.Recent

class RecentRepository(private val recentDAO: RecentDAO) {

    fun getUserRecentAl(userId: Int): LiveData<List<Recent>> = recentDAO.getUserRecent(userId)



    suspend fun insertRecent(recent: Recent){
        recentDAO.insertRecent(recent)
    }

    suspend fun deleteRecent(userId: Int, pokemonName: String){
        return recentDAO.deleteRecent(
            userId,
            pokemonName
        )
    }

}