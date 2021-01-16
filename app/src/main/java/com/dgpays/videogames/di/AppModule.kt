package com.dgpays.videogames.di

import com.dgpays.videogames.repository.Repository
import com.dgpays.videogames.retrofit.ResponseResultMapper
import com.dgpays.videogames.retrofit.VideoGameRetrofit
import com.dgpays.videogames.retrofit.entity.GameDescriptionMapper
import com.dgpays.videogames.room.RoomMapper
import com.dgpays.videogames.room.VideoGameDao
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
        roomMapper: RoomMapper,
        gameDescriptionMapper: GameDescriptionMapper,
        responseResultMapper: ResponseResultMapper,
        videoGameDao: VideoGameDao,
        videoGameRetrofit: VideoGameRetrofit,
    ): Repository {
        return Repository(
            roomMapper,
            gameDescriptionMapper,
            responseResultMapper,
            videoGameDao,
            videoGameRetrofit
        )
    }

}