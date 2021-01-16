package com.dgpays.videogames.model

data class VideoGame(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val metaCriticRate: Int,
    val releaseDate: String,
    val image: String,
    val isFavorite: Boolean
)
