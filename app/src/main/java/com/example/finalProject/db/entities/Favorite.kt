package com.example.finalProject.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("userId"),
    onDelete = ForeignKey.CASCADE
)]
)
class Favorite (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val name: String,
    val url: String
)