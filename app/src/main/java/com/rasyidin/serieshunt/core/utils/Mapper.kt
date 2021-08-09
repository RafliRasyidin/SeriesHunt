package com.rasyidin.serieshunt.core.utils

import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CastResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CrewResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.EpisodeResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.video.VideoItemResponse
import com.rasyidin.serieshunt.core.domain.model.*

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
            voteCount = it.voteCount,
            homepage = it.homepage
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
    voteCount = this.voteCount,
    homepage = this.homepage
)

fun List<CastResponse>.toListCast(): List<Cast> {
    val data = ArrayList<Cast>()
    this.map {
        val cast = Cast(
            character = it.character,
            id = it.id,
            name = it.name,
            profilePath = it.profilePath
        )
        data.add(cast)
    }
    return data
}

fun List<CrewResponse>.toListCrew(): List<Crew> {
    val data = ArrayList<Crew>()
    this.map {
        val crew = Crew(
            job = it.job,
            id = it.id,
            name = it.name,
            profilePath = it.profilePath
        )
        data.add(crew)
    }
    return data
}

fun List<VideoItemResponse>.toListVideoTrailer(): List<VideoTrailer> {
    val data = ArrayList<VideoTrailer>()
    this.map {
        val videoTrailer = VideoTrailer(
            id = it.id,
            key = it.key,
            site = it.site,
            type = it.type
        )
        data.add(videoTrailer)
    }
    return data
}

fun List<EpisodeResponse>.toListTvEpisode(): List<TvEpisode> {
    val data = ArrayList<TvEpisode>()
    this.map {
        val tvEpisode = TvEpisode(
            airDate = it.airDate,
            episodeNumber = it.episodeNumber,
            id = it.id,
            name = it.name,
            posterPath = it.posterPath
        )
        data.add(tvEpisode)
    }
    return data
}