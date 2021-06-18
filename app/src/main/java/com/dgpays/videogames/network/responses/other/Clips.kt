package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Clips(
    @SerializedName("320")
    @Expose
    var clip320: String,

    @SerializedName("640")
    @Expose
    var clip640: String,

    @SerializedName("full")
    @Expose
    var clipFull: String,
) : Serializable {
    constructor() : this(
        "",
        "",
        ""
    )
}
