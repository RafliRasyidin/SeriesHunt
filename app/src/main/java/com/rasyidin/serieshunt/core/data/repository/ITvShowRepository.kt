package com.rasyidin.serieshunt.core.data.repository

import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.SeasonResponse
import com.rasyidin.serieshunt.core.domain.ResultState
import com.rasyidin.serieshunt.core.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ITvShowRepository {

    val listAiringToday: StateFlow<ResultState<TvResult>>
    val listOnTheAir: StateFlow<ResultState<TvResult>>
    val listPopular: StateFlow<ResultState<TvResult>>
    val listTopRated: StateFlow<ResultState<TvResult>>

    suspend fun getAiringToday()

    suspend fun getOnTheAir()

    suspend fun getPopular()

    suspend fun getTopRated()

    fun getDetail(tvId: Int): Flow<Resource<TvShow>>

    fun searchTvShow(querySearch: String): Flow<Resource<List<TvShow>>>

    fun getCast(tvId: Int): Flow<Resource<List<Cast>>>

    fun getCrew(tvId: Int): Flow<Resource<List<Crew>>>

    fun getVideos(tvId: Int): Flow<Resource<List<VideoTrailer>>>

    fun getTvSeasons(tvId: Int, seasonNumber: Int): Flow<Resource<List<TvEpisode>>>
}