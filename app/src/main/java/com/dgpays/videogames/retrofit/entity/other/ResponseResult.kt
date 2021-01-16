package com.dgpays.videogames.retrofit.entity.other

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseResult(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("slug")
    @Expose
    var slug: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("released")
    @Expose
    var released: String,

    @SerializedName("tba")
    @Expose
    var tba: Boolean,

    @SerializedName("background_image")
    @Expose
    var backgroundImage: String,

    @SerializedName("rating")
    @Expose
    var rating: Double,

    @SerializedName("rating_top")
    @Expose
    var topRating: Int,

    @SerializedName("ratings")
    @Expose
    var ratings: List<Rating>,

    @SerializedName("ratings_count")
    @Expose
    var ratingsCount: Int,

    @SerializedName("reviews_text_count")
    @Expose
    var reviewsTextCount: Int,

    @SerializedName("added")
    @Expose
    var added: Int,

    @SerializedName("added_by_status")
    @Expose
    var addedByStatus: AddedByStatus,

    @SerializedName("metacritic")
    @Expose
    var metaCritic: Int,

    @SerializedName("playtime")
    @Expose
    var playtime: Int,

    @SerializedName("suggestions_count")
    @Expose
    var suggestionsCount: Int,

    @SerializedName("updated")
    @Expose
    var updated: String,

    @SerializedName("reviews_count")
    @Expose
    var reviewsCount: Int,

    @SerializedName("saturated_color")
    @Expose
    var saturatedColor: String,

    @SerializedName("dominant_color")
    @Expose
    var dominantColor: String,

    @SerializedName("platforms")
    @Expose
    var platforms: List<Platform>,

    @SerializedName("parent_platforms")
    @Expose
    var parentPlatforms: List<ParentPlatform>,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>,

    @SerializedName("stores")
    @Expose
    var stores: List<Store>,

    @SerializedName("clip")
    @Expose
    var clip: Clip,

    @SerializedName("tags")
    @Expose
    var tags: List<Tag>,

    @SerializedName("esrb_rating")
    @Expose
    var esrbRating: EsrbRating,

    @SerializedName("short_screenshots")
    @Expose
    var shortScreenshots: List<ShortScreenshot>,
) : Serializable {
    constructor(
        id: Int,
        name: String,
        rating: Double,
        metaCritic: Int,
        released: String,
        backgroundImage: String,
    ) : this(
        id,
        "",
        name,
        released,
        false,
        backgroundImage,
        rating,
        0,
        arrayListOf(),
        0,
        0,
        0,
        AddedByStatus(),
        metaCritic,
        0,
        0,
        "",
        0,
        "",
        "",
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        Clip(),
        arrayListOf(),
        EsrbRating(),
        arrayListOf()
    )
}