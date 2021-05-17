package com.example.finalProject.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_table")
data class PokemonRecent(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val pokemonName: String,
        val image: String)

