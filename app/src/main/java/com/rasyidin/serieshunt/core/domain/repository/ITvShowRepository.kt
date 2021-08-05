package com.rasyidin.serieshunt.core.domain.repository

import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.SeasonResponse
import com.rasyidin.serieshunt.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun getAiringToday(): Flow<Resource<List<TvShow>>>

    fun getOnTheAir(): Flow<Resource<List<TvShow>>>

    fun getPopular(): Flow<Resource<List<TvShow>>>

    fun getTopRated(): Flow<Resource<List<TvShow>>>

    fun getDetail(tvId: Int): Flow<Resource<TvShow>>

    fun searchTvShow(querySearch: String): Flow<Resource<List<TvShow>>>

    fun getCast(tvId: Int): Flow<Resource<List<Cast>>>

    fun getCrew(tvId: Int): Flow<Resource<List<Crew>>>

    fun getVideos(tvId: Int): Flow<Resource<List<VideoTrailer>>>

    fun getTvSeasons(tvId: Int, seasonNumber: Int): Flow<Resource<List<TvEpisode>>>
}