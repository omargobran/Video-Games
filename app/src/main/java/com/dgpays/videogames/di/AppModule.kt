package com.dgpays.videogames.di

import com.dgpays.videogames.repository.Repository
import com.dgpays.videogames.network.util.ResponseResultMapper
import com.dgpays.videogames.network.VideoGameRetrofit
import com.dgpays.videogames.network.util.VideoGameDtoMapper
import com.dgpays.videogames.cache.VideoGameEntityMapper
import com.dgpays.videogames.cache.VideoGameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        videoGameEntityMapper: VideoGameEntityMapper,
        videoGameDtoMapper: VideoGameDtoMapper,
        responseResultMapper: ResponseResultMapper,
        videoGameDao: VideoGameDao,
        videoGameRetrofit: VideoGameRetrofit,
    ): Repository {
        return Repository(
            videoGameEntityMapper,
            videoGameDtoMapper,
            responseResultMapper,
            videoGameDao,
            videoGameRetrofit
        )
    }

}