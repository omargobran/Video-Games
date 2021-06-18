package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Year(
    @SerializedName("from")
    @Expose
    var from: Int,

    @SerializedName("to")
    @Expose
    var to: Int,
    @SerializedName("filter")
    @Expose
    var filter: String,

    @SerializedName("decade")
    @Expose
    var decade: Int,

    @SerializedName("years")
    @Expose
    var years: List<SubYear>,

    @SerializedName("nofollow")
    @Expose
    var nofollow: Boolean,

    @SerializedName("count")
    @Expose
    var count: Int = 0
) : Serializable