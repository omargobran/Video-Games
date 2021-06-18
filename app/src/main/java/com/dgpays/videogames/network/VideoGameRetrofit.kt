package com.dgpays.videogames.network

import com.dgpays.videogames.network.responses.GamesResponse
import com.dgpays.videogames.network.model.VideoGameDto
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoGameRetrofit {

    @GET("games")
    suspend fun getVideoGames(): GamesResponse

    @GET("games/{id}")
    suspend fun getVideoGameDetails(@Path("id") id: Int): VideoGameDto

}