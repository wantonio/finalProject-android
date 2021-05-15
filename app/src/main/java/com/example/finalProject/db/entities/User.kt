package com.example.finalProject.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val userName: String,
        val name: String,
        val email: String,
        val password: String)

