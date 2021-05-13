package com.example.finalProject.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.finalProject.db.PokemonDatabase
import com.example.finalProject.db.dao.UserDAO
import com.example.finalProject.db.entities.User

class PokemonRepository(private val userDAO: UserDAO) {

    suspend fun insertUser(user: User){
        userDAO.insertUser(user)
    }

    fun getAllUser(): LiveData<List<User>> = userDAO.getAllUser()

    fun getUserById(emailUser: String, passwordUser: String): User = userDAO.getUserById(emailUser, passwordUser)

}