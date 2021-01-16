package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParentPlatform(
    @SerializedName("platform")
    @Expose
    var platform: SubParentPlatform
) : Serializable