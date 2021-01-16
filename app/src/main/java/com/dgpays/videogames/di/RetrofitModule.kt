package com.dgpays.videogames.di

import android.util.Log
import com.dgpays.videogames.retrofit.ResponseResultMapper
import com.dgpays.videogames.retrofit.VideoGameRetrofit
import com.dgpays.videogames.retrofit.entity.GameDescriptionMapper
import com.dgpays.videogames.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder().apply {
                addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                        Log.d(Constants.TAG, "Retrofit : $it")
                    }).setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                addInterceptor(Interceptor {
                    val request = it.request().newBuilder()
                        .addHeader("x-rapidapi-key",
                            "6a6fdc883bmshf118a899f5a4f82p1b74c8jsn6151fa84778d")
                        .addHeader("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
                        .addHeader("useQueryString", "true")

                    return@Interceptor it.proceed(request.build())
                })
            }.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rawg-video-games-database.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideVideoGameService(retrofit: Retrofit): VideoGameRetrofit {
        return retrofit.create(VideoGameRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun provideResponseResultMapperMapper(): ResponseResultMapper {
        return ResponseResultMapper()
    }

    @Singleton
    @Provides
    fun provideGameDescriptionMapper(): GameDescriptionMapper {
        return GameDescriptionMapper()
    }

}