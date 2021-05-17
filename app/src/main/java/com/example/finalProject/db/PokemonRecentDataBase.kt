package com.example.finalProject.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalProject.db.dao.PokemonRecentDAO
import com.example.finalProject.db.dao.UserDAO
import com.example.finalProject.db.entities.PokemonRecent
import com.example.finalProject.db.entities.User
import java.security.AccessControlContext

@Database(entities = [PokemonRecent::class], version = 1, exportSchema = false)
abstract class PokemonRecentDataBase : RoomDatabase(){

    abstract fun PokemonRecentDAO(): PokemonRecentDAO

    companion object{
        @Volatile
        private var INSTANCE: PokemonRecentDataBase? = null

        fun getDatabase(context: Context): PokemonRecentDataBase{
            val tmpInstance = INSTANCE
            if(tmpInstance != null){
                return  tmpInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonRecentDataBase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }

}
