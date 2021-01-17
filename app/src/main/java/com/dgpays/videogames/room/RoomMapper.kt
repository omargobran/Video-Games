package com.dgpays.videogames.room

import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.room.entity.VideoGameEntity
import com.dgpays.videogames.util.EntityMapper
import javax.inject.Inject

class RoomMapper: EntityMapper<VideoGameEntity, VideoGame> {

    override fun mapFromEntity(entity: VideoGameEntity): VideoGame {
        return VideoGame(
            entity.id,
            entity.title,
            entity.description,
            entity.rating,
            entity.metaCriticRate,
            entity.releaseDate,
            entity.image,
            entity.isFavorite == "true"
        )
    }

    override fun mapToEntity(domainModel: VideoGame): VideoGameEntity {
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
        return videoGameEntities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(videoGames: List<VideoGame>): List<VideoGameEntity> {
        return videoGames.map { mapToEntity(it) }
    }
}