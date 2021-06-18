package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Filter(
    @SerializedName("years")
    @Expose
    var years: List<Year>
) : Serializable