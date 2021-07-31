package com.rasyidin.serieshunt.core.utils

import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvItemResponse
import com.rasyidin.serieshunt.core.domain.model.TvShow

fun List<TvItemResponse>.toListTvShow(): List<TvShow> {
    val data = ArrayList<TvShow>()
    this.map {
        val tvShow = TvShow(
            id = it.id,
            backdropPath = it.backdropPath,
            episodeRunTime = emptyList(),
            firstAirDate = it.firstAirDate,
            genres = emptyList(),
            lastAirDate = it.lastAirDate,
            name = it.name,
            numberOfEpisodes = it.numberOfEpisodes,
            numberOfSeasons = it.numberOfSeasons,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            seasonResponses = emptyList(),
            status = it.status,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
        data.add(tvShow)
    }
    return data
}

fun TvItemResponse.toTvShow() = TvShow(
    id = this.id,
    backdropPath = this.backdropPath,
    episodeRunTime = this.episodeRunTime,
    firstAirDate = this.firstAirDate,
    genres = this.genres,
    lastAirDate = this.lastAirDate,
    name = this.name,
    numberOfEpisodes = this.numberOfEpisodes,
    numberOfSeasons = this.numberOfSeasons,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    seasonResponses = this.seasonResponses,
    status = this.status,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount
)