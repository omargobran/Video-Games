package com.dgpays.videogames.retrofit

import com.dgpays.videogames.model.VideoGame
import com.dgpays.videogames.retrofit.entity.other.ResponseResult
import com.dgpays.videogames.retrofit.entity.responses.GetGameResponse
import com.dgpays.videogames.util.EntityMapper

class ResponseResultMapper : EntityMapper<ResponseResult, VideoGame> {

    override fun mapFromEntity(entity: ResponseResult): VideoGame {
        return VideoGame(
            entity.id,
            entity.name,
            "",
            entity.rating,
            entity.metaCritic,
            entity.released,
            entity.backgroundImage,
            false
        )
    }

    override fun mapToEntity(domainModel: VideoGame): ResponseResult {
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
         return responseResults.map { mapFromEntity(it) }
    }
}