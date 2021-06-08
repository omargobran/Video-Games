package com.dgpays.videogames.di

import android.util.Log
import com.dgpays.videogames.BuildConfig
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    Log.d(Constants.TAG, "Retrofit : $it")
                }).setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(Interceptor {
                val request = it.request().newBuilder()
                val originalHttpUrl = it.request().url()
                val newUrl = originalHttpUrl.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()

                request.url(newUrl)
                return@Interceptor it.proceed(request.build())
            })
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .writeTimeout(45, TimeUnit.SECONDS)
            .build()
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
            .baseUrl("https://api.rawg.io/api/")
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