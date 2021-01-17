package com.dgpays.videogames.retrofit.entity

import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.retrofit.entity.responses.GetGameResponse
import com.dgpays.videogames.util.EntityMapper

class GameDescriptionMapper : EntityMapper<GetGameResponse, VideoGame> {

    override fun mapFromEntity(entity: GetGameResponse): VideoGame {
        return VideoGame(
            entity.id,
            entity.name,
            entity.rawDescription,
            entity.rating,
            entity.metaCritic,
            entity.released,
            entity.backgroundImage,
            false
        )
    }

    override fun mapToEntity(domainModel: VideoGame): GetGameResponse {
        return GetGameResponse(
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