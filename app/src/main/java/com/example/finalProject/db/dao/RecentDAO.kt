package com.example.finalProject.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalProject.db.entities.Recent

@Dao
interface RecentDAO {

    @Query("SELECT *,COUNT(*) AS userId FROM recent where userId == (:userId) GROUP BY name HAVING COUNT(*) >= 1 ORDER BY id DESC LIMIT 5")
     fun getUserRecent(userId: Int): LiveData<List<Recent>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecent(recent: Recent)

    @Query("DELETE FROM recent WHERE userId == (:userId) and name == (:pokemonName)")
    suspend fun deleteRecent(userId: Int, pokemonName: String)

    }

