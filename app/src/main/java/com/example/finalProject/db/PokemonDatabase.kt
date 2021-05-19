package com.example.finalProject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalProject.db.dao.FavoriteDAO
import com.example.finalProject.db.dao.RecentDAO
import com.example.finalProject.db.dao.UserDAO
import com.example.finalProject.db.entities.Favorite
import com.example.finalProject.db.entities.Recent
import com.example.finalProject.db.entities.User

@Database(entities = [User::class, Favorite::class, Recent::class], version = 1, exportSchema = false)
abstract class PokemonDatabase  : RoomDatabase(){

    abstract fun UserDAO():UserDAO
    abstract fun FavoriteDAO():FavoriteDAO
    abstract fun RecentDAO(): RecentDAO

    companion object{
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase{
            val tmpInstance = INSTANCE
            if(tmpInstance != null){
                return  tmpInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java,
                        "pokemon_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }

}