package com.dgpays.videogames.repository

import android.util.Log
import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.retrofit.ResponseResultMapper
import com.dgpays.videogames.retrofit.VideoGameRetrofit
import com.dgpays.videogames.retrofit.entity.GameDescriptionMapper
import com.dgpays.videogames.room.RoomMapper
import com.dgpays.videogames.room.VideoGameDao
import com.dgpays.videogames.util.Constants
import com.dgpays.videogames.util.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject
constructor(
    private val roomMapper: RoomMapper,
    private val gameDescriptionMapper: GameDescriptionMapper,
    private val responseResultMapper: ResponseResultMapper,
    private val videoGameDao: VideoGameDao,
    private val videoGameRetrofit: VideoGameRetrofit,
) {
    suspend fun getVideoGames(): Flow<State<List<VideoGame>>> = flow {
        emit(State.Loading)
        delay(2000)
        try {
            val oldCachedVideoGames = videoGameDao.getVideoGames()

            val networkVideoGames = videoGameRetrofit.getVideoGames()
            val videoGames = responseResultMapper.mapFromEntityList(networkVideoGames.results)

            videoGames.map { newVideoGame ->
                oldCachedVideoGames.map { oldVideoGame ->
                    if (newVideoGame.id == oldVideoGame.id) {
                        newVideoGame.isFavorite = (oldVideoGame.isFavorite == "true")
                    }
                }
            }

            videoGameDao.insertVideoGames(roomMapper.mapToEntityList(videoGames))
            val cachedVideoGames = videoGameDao.getVideoGames()

            emit(State.Success(roomMapper.mapFromEntityList(cachedVideoGames)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun getVideoGameDetails(id: Int): Flow<State<VideoGame>> = flow {
        emit(State.Loading)
        delay(2000)
        try {
            val oldCachedVideoGame = videoGameDao.getVideoGameById(id)

            val networkVideoGame = videoGameRetrofit.getVideoGameDetails(id)

            val videoGame = gameDescriptionMapper.mapFromEntity(networkVideoGame)

            videoGame.isFavorite = (oldCachedVideoGame.isFavorite == "true")

            val isUpdated = videoGameDao.update(roomMapper.mapToEntity(videoGame))
            Log.d(Constants.TAG, "getVideoGameById: updated rows = $isUpdated")

            val cachedVideoGame = videoGameDao.getVideoGameById(id)

            emit(State.Success(roomMapper.mapFromEntity(cachedVideoGame)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : ${exception.message}", exception)
            emit(State.Error(exception))
        }
    }

    suspend fun getVideoGamesFromRoom(): Flow<State<List<VideoGame>>> = flow {
        emit(State.Loading)
        try {
            val cachedVideoGames = videoGameDao.getVideoGames()
            emit(State.Success(roomMapper.mapFromEntityList(cachedVideoGames)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun getFavoriteVideoGames(): Flow<State<List<VideoGame>>> = flow {
        emit(State.Loading)
        try {
            val cachedFavoriteVideoGames = videoGameDao.getFavoriteVideoGames()
            emit(State.Success(roomMapper.mapFromEntityList(cachedFavoriteVideoGames)))
        } catch (exception: Exception) {
            Log.d(Constants.TAG, "getVideoGames: Exception : " + exception.message, exception)
            emit(State.Error(exception))
        }
    }

    suspend fun favoriteVideoGame(id: Int, favorite: Boolean): Flow<State<Boolean>> = flow {
        try {
            val selectedCachedVideoGame = videoGameDao.getVideoGameById(id)

            val selectedVideoGame = roomMapper.mapFromEntity(selectedCachedVideoGame)
            selectedVideoGame.isFavorite = favorite

            val updatedRowsCount = videoGameDao.update(roomMapper.mapToEntity(selectedVideoGame))
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