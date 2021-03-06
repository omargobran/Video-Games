package com.dgpays.videogames.cache

import androidx.room.*
import com.dgpays.videogames.cache.model.VideoGameEntity

@Dao
interface VideoGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGame(videoGameEntity: VideoGameEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGames(videoGameEntities: List<VideoGameEntity>): List<Long>

    @Update
    suspend fun update(videoGameEntity: VideoGameEntity): Int

    @Delete
    suspend fun delete(videoGameEntity: VideoGameEntity): Int

    @Query("SELECT * FROM video_games ORDER BY rating DESC")
    suspend fun getVideoGames(): List<VideoGameEntity>

    @Query("SELECT * FROM video_games WHERE favorite LIKE 'true' ORDER BY rating DESC")
    suspend fun getFavoriteVideoGames(): List<VideoGameEntity>

    @Query("SELECT * FROM video_games WHERE id LIKE :id")
    suspend fun getVideoGameById(id: Int): VideoGameEntity

    @Query("DELETE FROM video_games")
    suspend fun deleteAll(): Int

}