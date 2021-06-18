package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubParentPlatform(
    @SerializedName(value = "id", alternate = ["platform"])
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("slug")
    @Expose
    var slug: String,
) : Serializable