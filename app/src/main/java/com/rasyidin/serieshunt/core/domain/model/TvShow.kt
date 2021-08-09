package com.rasyidin.serieshunt.core.domain.model

import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.Genre
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.SeasonResponse

data class TvShow(
    val id: Int = 0,
    val backdropPath: String? = "",
    val episodeRunTime: List<Int>,
    val firstAirDate: String? = "",
    val genres: List<Genre>,
    val lastAirDate: String? = "",
    val name: String? = "",
    val numberOfEpisodes: Int = 0,
    val numberOfSeasons: Int = 0,
    val overview: String? = "",
    val popularity: Double = 0.0,
    val posterPath: String? = "",
    val seasonResponses: List<SeasonResponse>,
    val status: String? = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val homepage: String? = ""
)
