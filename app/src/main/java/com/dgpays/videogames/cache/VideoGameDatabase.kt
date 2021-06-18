package com.dgpays.videogames.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dgpays.videogames.cache.model.VideoGameEntity

@Database(entities = [VideoGameEntity::class], version = 2)
abstract class VideoGameDatabase : RoomDatabase() {
    abstract fun videoGameDao(): VideoGameDao

    companion object {
        const val DATABASE_NAME = "video_game_db"
    }
}