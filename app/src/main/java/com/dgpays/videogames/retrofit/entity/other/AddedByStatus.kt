package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddedByStatus(
    @SerializedName("yet")
    @Expose
    var yet: Int,

    @SerializedName("owned")
    @Expose
    var owned: Int,

    @SerializedName("beaten")
    @Expose
    var beaten: Int,

    @SerializedName("toplay")
    @Expose
    var toplay: Int,

    @SerializedName("dropped")
    @Expose
    var dropped: Int,

    @SerializedName("playing")
    @Expose
    var playing: Int
) : Serializable {
    constructor(): this(
        0,
        0,
        0,
        0,
        0,
        0
    )
}