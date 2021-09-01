package com.rasyidin.serieshunt.core.domain.usecase

import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.data.repository.ITvShowRepository
import com.rasyidin.serieshunt.core.domain.ResultState
import com.rasyidin.serieshunt.core.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TvShowInteractors @Inject constructor(private val tvShowRepository: ITvShowRepository) :
    ITvShowUseCase {

    override val listAiringToday: StateFlow<ResultState<TvResult>>
        get() = tvShowRepository.listAiringToday
    override val listOnTheAir: StateFlow<ResultState<TvResult>>
        get() = tvShowRepository.listOnTheAir
    override val listPopular: StateFlow<ResultState<TvResult>>
        get() = tvShowRepository.listPopular
    override val listTopRated: StateFlow<ResultState<TvResult>>
        get() = tvShowRepository.listTopRated

    override suspend fun getAiringToday() {
        tvShowRepository.getAiringToday()
    }

    override suspend fun getOnTheAir() {
        tvShowRepository.getOnTheAir()
    }

    override suspend fun getPopular() {
        tvShowRepository.getPopular()
    }

    override suspend fun getTopRated() {
        tvShowRepository.getTopRated()
    }

    override fun getDetail(idTv: Int): Flow<Resource<TvShow>> {
        return tvShowRepository.getDetail(idTv)
    }

    override fun searchTvShow(querySearch: String): Flow<Resource<List<TvShow>>> {
        return tvShowRepository.searchTvShow(querySearch)
    }

    override fun getCast(tvId: Int): Flow<Resource<List<Cast>>> {
        return tvShowRepository.getCast(tvId)
    }

    override fun getCrew(tvId: Int): Flow<Resource<List<Crew>>> {
        return tvShowRepository.getCrew(tvId)
    }

    override fun getVideos(tvId: Int): Flow<Resource<List<VideoTrailer>>> {
        return tvShowRepository.getVideos(tvId)
    }

    override fun getTvSeasons(tvId: Int, seasonNumber: Int): Flow<Resource<List<TvEpisode>>> {
        return tvShowRepository.getTvSeasons(tvId, seasonNumber)
    }
}