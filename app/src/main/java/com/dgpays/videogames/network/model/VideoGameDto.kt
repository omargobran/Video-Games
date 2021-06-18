package com.dgpays.videogames.network.model

import com.dgpays.videogames.network.responses.other.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.math.roundToInt

data class VideoGameDto(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("slug")
    @Expose
    var slug: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("name_original")
    @Expose
    var originalName: String,

    @SerializedName("description")
    @Expose
    var htmlDescription: String,

    @SerializedName("metacritic")
    @Expose
    var metaCritic: Int,

    @SerializedName("metacritic_platforms")
    @Expose
    var metaCriticPlatforms: List<MetaCriticPlatform>,

    @SerializedName("released")
    @Expose
    var released: String,

    @SerializedName("tba")
    @Expose
    var tba: Boolean,

    @SerializedName("updated")
    @Expose
    var updateDate: String,

    @SerializedName("background_image")
    @Expose
    var backgroundImage: String,

    @SerializedName("background_image_additional")
    @Expose
    var additionalBackgroundImage: String,

    @SerializedName("website")
    @Expose
    var website: String,

    @SerializedName("rating")
    @Expose
    var rating: Double,

    @SerializedName("rating_top")
    @Expose
    var topRating: Int,

    @SerializedName("ratings")
    @Expose
    var ratings: List<Rating>,

    @SerializedName("added")
    @Expose
    var added: Int,

    @SerializedName("added_by_status")
    @Expose
    var addedByStatus: AddedByStatus,

    @SerializedName("playtime")
    @Expose
    var playtime: Int,

    @SerializedName("screenshots_count")
    @Expose
    var screenshotsCount: Int,

    @SerializedName("movies_count")
    @Expose
    var moviesCount: Int,

    @SerializedName("creators_count")
    @Expose
    var creatorsCount: Int,

    @SerializedName("achievements_count")
    @Expose
    var achievementsCount: Int,

    @SerializedName("parent_achievements_count")
    @Expose
    var parentAchievementsCount: Int,

    @SerializedName("reddit_url")
    @Expose
    var redditUrl: String,

    @SerializedName("reddit_name")
    @Expose
    var redditName: String,

    @SerializedName("reddit_description")
    @Expose
    var redditDescription: String,

    @SerializedName("reddit_logo")
    @Expose
    var redditLogo: String,

    @SerializedName("reddit_count")
    @Expose
    var redditCount: Int,

    @SerializedName("twitch_count")
    @Expose
    var twitchCount: Int,

    @SerializedName("youtube_count")
    @Expose
    var youtubeCount: Int,

    @SerializedName("reviews_text_count")
    @Expose
    var textReviewsCount: Int,

    @SerializedName("ratings_count")
    @Expose
    var ratingsCount: Int,

    @SerializedName("suggestions_count")
    @Expose
    var suggestionsCount: Int,

    @SerializedName("alternative_names")
    @Expose
    var alternativeNames: List<String>,

    @SerializedName("metacritic_url")
    @Expose
    var metaCriticUrl: String,

    @SerializedName("parents_count")
    @Expose
    var parentsCount: Int,

    @SerializedName("additions_count")
    @Expose
    var additionsCount: Int,

    @SerializedName("game_series_count")
    @Expose
    var gameSeriesCount: Int,

    @SerializedName("reviews_count")
    @Expose
    var reviewsCount: Int,

    @SerializedName("saturated_color")
    @Expose
    var saturatedColor: String,

    @SerializedName("dominant_color")
    @Expose
    var dominantColor: String,

    @SerializedName("parent_platforms")
    @Expose
    var parentPlatforms: List<ParentPlatform>,

    @SerializedName("platforms")
    @Expose
    var platforms: List<Platform>,

    @SerializedName("stores")
    @Expose
    var stores: List<Store>,

    @SerializedName("developers")
    @Expose
    var developers: List<Developer>,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>,

    @SerializedName("tags")
    @Expose
    var tags: List<Tag>,

    @SerializedName("publishers")
    @Expose
    var publishers: List<Publisher>,

    @SerializedName("esrb_rating")
    @Expose
    var esrbRating: EsrbRating,

    @SerializedName("clip")
    @Expose
    var clip: Clip,

    @SerializedName("description_raw")
    @Expose
    var rawDescription: String,
) : Serializable {
    constructor(
        id: Int,
        name: String,
        description: String,
        rating: Double,
        metaCritic: Int,
        released: String,
        backgroundImage: String,
    ) : this(
        id,
        "",
        name,
        "",
        description,
        metaCritic,
        arrayListOf(),
        released,
        false,
        "",
        backgroundImage,
        backgroundImage,
        "",
        rating,
        rating.roundToInt(),
        arrayListOf(),
        0,
        AddedByStatus(),
        0,
        0,
        0,
        0,
        0,
        0,
        "",
        "",
        "",
        "",
        0,
        0,
        0,
        0,
        0,
        0,
        arrayListOf(),
        "",
        0,
        0,
        0,
        0,
        "",
        "",
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        arrayListOf(),
        EsrbRating(),
        Clip(),
        description

    )
}