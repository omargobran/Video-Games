package com.dgpays.videogames.network.responses

import com.dgpays.videogames.network.responses.other.Filter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GamesResponse(
    @SerializedName("count")
    @Expose
    var count: Int,

    @SerializedName("next")
    @Expose
    var next: String,

    @SerializedName("results")
    @Expose
    var results: List<ResponseResult>,

    @SerializedName("seo_title")
    @Expose
    var seoTitle: String,

    @SerializedName("seo_description")
    @Expose
    var seoDescription: String,

    @SerializedName("seo_keywords")
    @Expose
    var seoKeywords: String,

    @SerializedName("seo_h1")
    @Expose
    var seoH1: String,

    @SerializedName("noindex")
    @Expose
    var noIndex: Boolean,

    @SerializedName("nofollow")
    @Expose
    var noFollow: Boolean,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("filters")
    @Expose
    var filters: Filter,

    @SerializedName("nofollow_collections")
    @Expose
    var noFollowCollections: List<String>,
) : Serializable