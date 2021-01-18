package com.dgpays.videogames.retrofit

import com.dgpays.videogames.retrofit.entity.responses.GamesResponse
import com.dgpays.videogames.retrofit.entity.other.ResponseResult
import com.dgpays.videogames.retrofit.entity.responses.GetGameResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface VideoGameRetrofit {

    @GET("games")
    suspend fun getVideoGames(): GamesResponse

    @GET("games/{id}")
    suspend fun getVideoGameDetails(@Path("id") id: Int): GetGameResponse

}