package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubYear(
    @SerializedName("year")
    @Expose
    var year: Int,

    @SerializedName("count")
    @Expose
    var count: Int,

    @SerializedName("nofollow")
    @Expose
    var nofollow: Boolean
) : Serializable
