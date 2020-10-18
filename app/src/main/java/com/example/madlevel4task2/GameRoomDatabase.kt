package com.example.madlevel4task2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao() : GameDao

    companion object{
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var gameRoomDatabaseInstace: GameRoomDatabase? = null

        fun getDatabase(context: Context) : GameRoomDatabase?{
            if (gameRoomDatabaseInstace == null){
                synchronized(GameRoomDatabase::class.java){
                    if (gameRoomDatabaseInstace == null){
                        gameRoomDatabaseInstace = Room.databaseBuilder(context.applicationContext, GameRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }
            return gameRoomDatabaseInstace
        }
    }
}