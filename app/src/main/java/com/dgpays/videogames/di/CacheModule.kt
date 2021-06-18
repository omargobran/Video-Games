package com.dgpays.videogames.di

import android.content.Context
import androidx.room.Room
import com.dgpays.videogames.cache.VideoGameEntityMapper
import com.dgpays.videogames.cache.VideoGameDao
import com.dgpays.videogames.cache.VideoGameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideVideoGameDb(@ApplicationContext context: Context): VideoGameDatabase {
        return Room.databaseBuilder(
            context,
            VideoGameDatabase::class.java,
            VideoGameDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideVideoGameDao(videoGameDatabase: VideoGameDatabase): VideoGameDao {
        return videoGameDatabase.videoGameDao()
    }

    @Singleton
    @Provides
    fun provideRoomMapper(): VideoGameEntityMapper {
        return VideoGameEntityMapper()
    }

}