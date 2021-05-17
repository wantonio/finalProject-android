package com.example.finalProject.db.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.db.entities.User

data class UserFavorites (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val favorites: List<Favorite>
    )