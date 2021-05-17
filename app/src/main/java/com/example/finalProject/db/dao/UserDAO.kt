package com.example.finalProject.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalProject.db.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM user_table where email == (:emailUser) and password == (:passwordUser)")
    suspend fun getUserById(emailUser: String, passwordUser: String): User

}