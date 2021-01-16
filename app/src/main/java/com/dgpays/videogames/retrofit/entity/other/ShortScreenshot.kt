package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShortScreenshot(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("image")
    @Expose
    var image: String
) : Serializable
