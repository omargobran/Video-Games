package com.dgpays.videogames.repository

import android.util.Log
import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.network.util.ResponseResultMapper
import com.dgpays.videogames.network.VideoGameRetrofit
import com.dgpays.videogames.network.util.VideoGameDtoMapper
import com.dgpays.videogames.cache.VideoGameEntityMapper
import com.dgpays.videogames.cache.VideoGameDao
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository(
    private val videoGameEntityMapper: VideoGameEntityMapper,
    private val videoGameDtoMapper: VideoGameDtoMapper,
    private val responseResultMapper: ResponseResultMapper,
    private val videoGameDao: VideoGameDao,
    private val videoGameRetrofit: VideoGameRetrofit,
) {
    suspend fun getVideoGames(): Flow<State<List<VideoGame>>> = flow {
        emit(State.Loading)
        try {
            val oldCachedVideoGames = videoGameDao.getVideoGames()

            val networkVideoGames = videoGameRetrofit.getVideoGames()
            val videoGames = responseResultMapper.mapToDomainList(networkVideoGames.results)

            videoGames.map { newVideoGame ->
                oldCachedVideoGames.map { oldVideoGame ->
                    if (newVideoGame.id == oldVideoGame.id) {
                        newVideoGame.isFavorite = (oldVideoGame.isFavorite == "true")
                    }
                }
            }

            videoGameDao.insertVideoGames(videoGameEntityMapper.mapFromDomainList(videoGames))
            val cachedVideoGames = videoGameDao.getVideoGames()

            emit(State.Success(videoGameEntityMapper.mapToDomainList(cachedVideoGames)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun getVideoGameDetails(id: Int): Flow<State<VideoGame>> = flow {
        emit(State.Loading)
        try {
            val oldCachedVideoGame = videoGameDao.getVideoGameById(id)

            val networkVideoGame = videoGameRetrofit.getVideoGameDetails(id)

            val videoGame = videoGameDtoMapper.mapToDomainModel(networkVideoGame)

            videoGame.isFavorite = (oldCachedVideoGame.isFavorite == "true")

            val isUpdated = videoGameDao.update(videoGameEntityMapper.mapFromDomainModel(videoGame))
            Log.d(Constants.TAG, "getVideoGameById: updated rows = $isUpdated")

            val cachedVideoGame = videoGameDao.getVideoGameById(id)

            emit(State.Success(videoGameEntityMapper.mapToDomainModel(cachedVideoGame)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : ${exception.message}", exception)
            emit(State.Error(exception))
        }
    }

    suspend fun getVideoGamesFromRoom(): Flow<State<List<VideoGame>>> = flow {
        emit(State.Loading)
        try {
            val cachedVideoGames = videoGameDao.getVideoGames()
            emit(State.Success(videoGameEntityMapper.mapToDomainList(cachedVideoGames)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun getFavoriteVideoGames(): Flow<State<List<VideoGame>>> = flow {
        emit(State.Loading)
        try {
            val cachedFavoriteVideoGames = videoGameDao.getFavoriteVideoGames()
            emit(State.Success(videoGameEntityMapper.mapToDomainList(cachedFavoriteVideoGames)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun favoriteVideoGame(id: Int, favorite: Boolean): Flow<State<Boolean>> = flow {
        try {
            val selectedCachedVideoGame = videoGameDao.getVideoGameById(id)

            val selectedVideoGame = videoGameEntityMapper.mapToDomainModel(selectedCachedVideoGame)
            selectedVideoGame.isFavorite = favorite

            val updatedRowsCount =
                videoGameDao.update(videoGameEntityMapper.mapFromDomainModel(selectedVideoGame))
            Log.d(Constants.TAG, "getVideoGameById: updated rows = $updatedRowsCount")
            emit(State.Success(updatedRowsCount > 0))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun deleteAllVideoGames(): Flow<State<Boolean>> = flow {
        try {
            val updatedRowsCount = videoGameDao.deleteAll()
            Log.d(Constants.TAG, "getVideoGameById: updated rows = $updatedRowsCount")
            emit(State.Success(updatedRowsCount > 0))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }
}