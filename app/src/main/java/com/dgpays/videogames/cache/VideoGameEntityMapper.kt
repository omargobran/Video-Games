package com.dgpays.videogames.cache

import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.cache.model.VideoGameEntity
import com.dgpays.videogames.domain.util.EntityMapper

class VideoGameEntityMapper: EntityMapper<VideoGameEntity, VideoGame> {

    override fun mapToDomainModel(model: VideoGameEntity): VideoGame {
        return VideoGame(
            model.id,
            model.title,
            model.description,
            model.rating,
            model.metaCriticRate,
            model.releaseDate,
            model.image,
            model.isFavorite == "true"
        )
    }

    override fun mapFromDomainModel(domainModel: VideoGame): VideoGameEntity {
        return VideoGameEntity(
            domainModel.id,
            domainModel.title,
            domainModel.description,
            domainModel.rating,
            domainModel.metaCriticRate,
            domainModel.releaseDate,
            domainModel.image,
            domainModel.isFavorite.toString()
        )
    }

    fun mapFromEntityList(videoGameEntities: List<VideoGameEntity>): List<VideoGame> {
        return videoGameEntities.map { mapToDomainModel(it) }
    }

    fun mapToEntityList(videoGames: List<VideoGame>): List<VideoGameEntity> {
        return videoGames.map { mapFromDomainModel(it) }
    }
}