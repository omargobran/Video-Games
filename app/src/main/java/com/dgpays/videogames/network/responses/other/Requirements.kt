package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Requirements(
    @SerializedName("minimum")
    @Expose
    var minimum: String,

    @SerializedName("recommended")
    @Expose
    var recommended: String
) : Serializable
