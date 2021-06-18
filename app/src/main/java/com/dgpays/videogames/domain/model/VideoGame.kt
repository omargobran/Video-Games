package com.dgpays.videogames.domain.model

import java.io.Serializable

data class VideoGame(
    var id: Int,
    var title: String,
    var description: String,
    var rating: Double,
    var metaCriticRate: Int,
    var releaseDate: String,
    var image: String,
    var isFavorite: Boolean
) : Serializable
