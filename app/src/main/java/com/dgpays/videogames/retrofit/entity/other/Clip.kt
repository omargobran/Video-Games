package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Clip(
    @SerializedName("clip")
    @Expose
    var clip: String,

    @SerializedName("clips")
    @Expose
    var clips: Clips,

    @SerializedName("video")
    @Expose
    var video: String,

    @SerializedName("preview")
    @Expose
    var preview: String,
) : Serializable {
    constructor() : this(
        "",
        Clips(),
        "",
        ""
    )
}
