package com.example.finalProject.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent")
data class Recent (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val userId: Int,
        val name: String,
        val url: String
)

