package com.example.finalProject.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.db.models.UserFavorites

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorite where userId == (:userId)")
    suspend fun getUserFavorites(userId: Int): List<Favorite>

    @Query("SELECT id FROM favorite where userId == (:userId) and name == (:pokemonName)")
    suspend fun isPokemonFavorite(userId: Int, pokemonName: String): List<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorites: Favorite)

    @Query("DELETE FROM favorite WHERE userId == (:userId) and name == (:pokemonName)")
    suspend fun deleteFavorite(userId: Int, pokemonName: String)
}