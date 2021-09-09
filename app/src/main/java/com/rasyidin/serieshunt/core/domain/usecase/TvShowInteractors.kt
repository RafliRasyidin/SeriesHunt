package com.rasyidin.serieshunt.core.domain.usecase

import com.rasyidin.serieshunt.core.data.repository.ITvShowRepository
import com.rasyidin.serieshunt.core.domain.ResultState
import com.rasyidin.serieshunt.core.domain.model.*
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

    override val tvShow: StateFlow<ResultState<TvShow>>
        get() = tvShowRepository.tvShow

    override val listCast: StateFlow<ResultState<List<Cast>>>
        get() = tvShowRepository.listCast

    override val listCrew: StateFlow<ResultState<List<Crew>>>
        get() = tvShowRepository.listCrew

    override val videos: StateFlow<ResultState<List<VideoTrailer>>>
        get() = tvShowRepository.videos

    override val tvEpisodes: StateFlow<ResultState<List<TvEpisode>>>
        get() = tvShowRepository.tvEpisodes

    override val searchResults: StateFlow<ResultState<TvResult>>
        get() = tvShowRepository.searchResults

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

    override suspend fun getDetail(idTv: Int) {
        return tvShowRepository.getDetail(idTv)
    }

    override suspend fun searchTvShow(querySearch: String) {
        return tvShowRepository.searchTvShow(querySearch)
    }

    override suspend fun getCast(tvId: Int) {
        return tvShowRepository.getCast(tvId)
    }

    override suspend fun getCrew(tvId: Int) {
        return tvShowRepository.getCrew(tvId)
    }

    override suspend fun getVideos(tvId: Int) {
        return tvShowRepository.getVideos(tvId)
    }

    override suspend fun getTvSeasons(tvId: Int, seasonNumber: Int) {
        return tvShowRepository.getTvSeasons(tvId, seasonNumber)
    }
}