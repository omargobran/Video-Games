package com.dgpays.videogames.network.util

import com.dgpays.videogames.domain.model.VideoGame
import com.dgpays.videogames.network.responses.ResponseResult
import com.dgpays.videogames.domain.util.EntityMapper

class ResponseResultMapper : EntityMapper<ResponseResult, VideoGame> {

    override fun mapToDomainModel(model: ResponseResult): VideoGame {
        return VideoGame(
            model.id,
            model.name,
            "",
            model.rating,
            model.metaCritic,
            model.released,
            model.backgroundImage,
            false
        )
    }

    override fun mapFromDomainModel(domainModel: VideoGame): ResponseResult {
        return ResponseResult(
            domainModel.id,
            domainModel.title,
            domainModel.rating,
            domainModel.metaCriticRate,
            domainModel.releaseDate,
            domainModel.image
        )
    }

    fun mapFromEntityList(responseResults: List<ResponseResult>): List<VideoGame> {
         return responseResults.map { mapToDomainModel(it) }
    }
}