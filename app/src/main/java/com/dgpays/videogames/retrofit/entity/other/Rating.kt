package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("count")
    @Expose
    var count: Int,

    @SerializedName("percent")
    @Expose
    var percent: Double,
) : Serializable