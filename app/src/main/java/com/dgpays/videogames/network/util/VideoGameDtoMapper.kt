package com.dgpays.videogames.network.util

import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.network.model.VideoGameDto
import com.dgpays.videogames.domain.util.EntityMapper

class VideoGameDtoMapper : EntityMapper<VideoGameDto, VideoGame> {

    override fun mapToDomainModel(model: VideoGameDto): VideoGame {
        return VideoGame(
            model.id,
            model.name,
            model.rawDescription,
            model.rating,
            model.metaCritic,
            model.released,
            model.backgroundImage,
            false
        )
    }

    override fun mapFromDomainModel(domainModel: VideoGame): VideoGameDto {
        return VideoGameDto(
            domainModel.id,
            domainModel.title,
            domainModel.description,
            domainModel.rating,
            domainModel.metaCriticRate,
            domainModel.releaseDate,
            domainModel.image
        )
    }

}