package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Platform(
    @SerializedName("platform")
    @Expose
    var platform: SubPlatform,

    @SerializedName("released_at")
    @Expose
    var releasedAt: String,

    @SerializedName("requirements_en", alternate = ["requirements"])
    @Expose
    var requirements: Requirements
) : Serializable