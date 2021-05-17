package com.example.finalProject.repositories

import androidx.lifecycle.LiveData
import com.example.finalProject.db.dao.FavoriteDAO
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.db.models.UserFavorites
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {
    suspend fun getUserFavorites(userId: Int): List<Favorite> = favoriteDAO.getUserFavorites(userId)

    suspend fun isPokemonFavorite(userId: Int, pokemonName: String): List<Int>{
        return favoriteDAO.isPokemonFavorite(
            userId,
            pokemonName
        )
    }

    suspend fun insertFavorite(favorite: Favorite){
        favoriteDAO.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(userId: Int, pokemonName: String){
        return favoriteDAO.deleteFavorite(
            userId,
            pokemonName
        )
    }
}