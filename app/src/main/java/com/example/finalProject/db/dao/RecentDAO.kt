package com.example.finalProject.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalProject.db.entities.Recent

@Dao
interface RecentDAO {

    @Query("SELECT * FROM recent where userId == (:userId)")
    suspend fun getUserRecents(userId: Int): List<Recent>

    @Query("SELECT id FROM recent where userId == (:userId) and name == (:pokemonName)")
    suspend fun isPokemonRecent(userId: Int, pokemonName: String): List<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecent(recents: Recent)

    @Query("DELETE FROM recent WHERE userId == (:userId) and name == (:pokemonName)")
    suspend fun deleteRecent(userId: Int, pokemonName: String)

    }

