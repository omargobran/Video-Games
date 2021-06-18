package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MetaCriticPlatform(
    @SerializedName("metascore")
    @Expose
    var metaScore: Int,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("platform")
    @Expose
    var metaCritic: SubParentPlatform,
)
