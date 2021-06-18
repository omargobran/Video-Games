package com.dgpays.videogames.network.responses.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Store(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("store")
    @Expose
    var store: SubStore
) : Serializable