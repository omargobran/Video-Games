package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubPlatform(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("slug")
    @Expose
    var slug: String,

    @SerializedName("image")
    @Expose
    var image: String,

    @SerializedName("year_end")
    @Expose
    var yearEnd: Int,

    @SerializedName("year_start")
    @Expose
    var yearStart: Int,

    @SerializedName("games_count")
    @Expose
    var gamesCount: Int,

    @SerializedName("image_background")
    @Expose
    var imageBackground: String
) : Serializable