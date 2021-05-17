package com.example.finalProject.db.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.finalProject.db.entities.Recent
import com.example.finalProject.db.entities.User

data class UserRecents (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val recents: List<Recent>
    )