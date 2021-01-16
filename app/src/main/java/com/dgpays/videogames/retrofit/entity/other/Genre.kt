package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Genre(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("slug")
    @Expose
    var slug: String,
    @SerializedName("games_count")
    @Expose
    var gamesCount: Int,
    @SerializedName("image_background")
    @Expose
    var imageBackground: String
) : Serializable