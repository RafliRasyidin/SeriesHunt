package com.rasyidin.serieshunt.core.data.repository

import com.rasyidin.serieshunt.core.domain.ResultState
import com.rasyidin.serieshunt.core.domain.model.*
import kotlinx.coroutines.flow.StateFlow

interface ITvShowRepository {

    val listAiringToday: StateFlow<ResultState<TvResult>>
    val listOnTheAir: StateFlow<ResultState<TvResult>>
    val listPopular: StateFlow<ResultState<TvResult>>
    val listTopRated: StateFlow<ResultState<TvResult>>
    val tvShow: StateFlow<ResultState<TvShow>>
    val listCast: StateFlow<ResultState<List<Cast>>>
    val listCrew: StateFlow<ResultState<List<Crew>>>
    val videos: StateFlow<ResultState<List<VideoTrailer>>>
    val tvEpisodes: StateFlow<ResultState<List<TvEpisode>>>
    val searchResults: StateFlow<ResultState<TvResult>>

    suspend fun getAiringToday()

    suspend fun getOnTheAir()

    suspend fun getPopular()

    suspend fun getTopRated()

    suspend fun getDetail(tvId: Int)

    suspend fun searchTvShow(querySearch: String)

    suspend fun getCast(tvId: Int)

    suspend fun getCrew(tvId: Int)

    suspend fun getVideos(tvId: Int)

    suspend fun getTvSeasons(tvId: Int, seasonNumber: Int)
}