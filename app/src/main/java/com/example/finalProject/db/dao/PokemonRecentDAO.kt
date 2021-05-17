package com.example.finalProject.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalProject.db.entities.PokemonRecent

@Dao
interface PokemonRecentDAO {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertPokemon(pokemon: PokemonRecent)

        @Query("SELECT * FROM recent_table")
        fun getAllPokemon(): LiveData<List<PokemonRecent>>

       // @Query("SELECT * FROM user_table where email == (:emailUser) and password == (:passwordUser)")
        //fun getUserById(emailUser: String, passwordUser: String): PokemonRecent

    }

