package com.dgpays.videogames.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_games")
data class VideoGameEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "meta_critic_rate")
    val metaCriticRate: Int,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "favorite")
    val isFavorite: String
)
