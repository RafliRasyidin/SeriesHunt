package com.rasyidin.serieshunt.core.data.repository

import com.rasyidin.serieshunt.core.data.source.remote.RemoteDataSource
import com.rasyidin.serieshunt.core.domain.ResultState
import com.rasyidin.serieshunt.core.domain.model.*
import com.rasyidin.serieshunt.core.utils.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class TvShowRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ITvShowRepository {

    private val _listAiringToday: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _listOnTheAir: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _listPopular: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _listTopRated: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _tvShow: MutableStateFlow<ResultState<TvShow>> = idle()
    private val _listCast: MutableStateFlow<ResultState<List<Cast>>> = idle()
    private val _listCrew: MutableStateFlow<ResultState<List<Crew>>> = idle()
    private val _videos: MutableStateFlow<ResultState<List<VideoTrailer>>> = idle()
    private val _tvEpisodes: MutableStateFlow<ResultState<List<TvEpisode>>> = idle()
    private val _searchResults: MutableStateFlow<ResultState<TvResult>> = idle()

    override val listAiringToday: StateFlow<ResultState<TvResult>>
        get() = _listAiringToday

    override val listOnTheAir: StateFlow<ResultState<TvResult>>
        get() = _listOnTheAir

    override val listPopular: StateFlow<ResultState<TvResult>>
        get() = _listPopular

    override val listTopRated: StateFlow<ResultState<TvResult>>
        get() = _listTopRated

    override val tvShow: StateFlow<ResultState<TvShow>>
        get() = _tvShow

    override val listCast: StateFlow<ResultState<List<Cast>>>
        get() = _listCast

    override val listCrew: StateFlow<ResultState<List<Crew>>>
        get() = _listCrew

    override val videos: StateFlow<ResultState<List<VideoTrailer>>>
        get() = _videos

    override val tvEpisodes: StateFlow<ResultState<List<TvEpisode>>>
        get() = _tvEpisodes

    override val searchResults: StateFlow<ResultState<TvResult>>
        get() = _searchResults

    override suspend fun getAiringToday() {
        fetch {
            remoteDataSource.getAiringToday()
        }.map { result ->
            mapResult(result) {
                this.toTvResult()
            }
        }.collect { result ->
            _listAiringToday.value = result
        }
    }

    override suspend fun getOnTheAir() {
        fetch {
            remoteDataSource.getOnTheAir()
        }.map { result ->
            mapResult(result) {
                this.toTvResult()
            }
        }.collect { result ->
            _listOnTheAir.value = result
        }
    }

    override suspend fun getPopular() {
        fetch {
            remoteDataSource.getPopular()
        }.map { result ->
            mapResult(result) {
                this.toTvResult()
            }
        }.collect { result ->
            _listPopular.value = result
        }
    }

    override suspend fun getTopRated() {
        fetch {
            remoteDataSource.getTopRated()
        }.map { result ->
            mapResult(result) {
                this.toTvResult()
            }
        }.collect { result ->
            _listTopRated.value = result
        }
    }

    override suspend fun getDetail(tvId: Int) {
        fetch {
            remoteDataSource.getDetail(tvId)
        }.map { result ->
            mapResult(result) {
                this.toTvShow()
            }
        }.collect { result ->
            _tvShow.value = result
        }
    }

    override suspend fun searchTvShow(querySearch: String) {
        fetch {
            remoteDataSource.searchTvShow(querySearch)
        }.map { result ->
            mapResult(result) {
                this.toTvResult()
            }
        }.collect { result ->
            _searchResults.value = result
        }
    }

    override suspend fun getCast(tvId: Int) {
        fetch {
            remoteDataSource.getCredits(tvId).castResponse
        }.map { result ->
            mapResult(result) {
                this.toListCast()
            }
        }.collect { result ->
            _listCast.value = result
        }
    }

    override suspend fun getCrew(tvId: Int) {
        fetch {
            remoteDataSource.getCredits(tvId).crewResponse
        }.map { result ->
            mapResult(result) {
                this.toListCrew()
            }
        }.collect { result ->
            _listCrew.value = result
        }
    }

    override suspend fun getVideos(tvId: Int) {
        fetch {
            remoteDataSource.getVideos(tvId).videoItemResponses
        }.map { result ->
            mapResult(result) {
                this.toListVideoTrailer()
            }
        }.collect { result ->
            _videos.value = result
        }
    }

    override suspend fun getTvSeasons(tvId: Int, seasonNumber: Int) {
        fetch {
            remoteDataSource.getTvSeasons(tvId, seasonNumber).episodeResponses
        }.map { result ->
            mapResult(result) {
                this.toListTvEpisode()
            }
        }.collect { result ->
            _tvEpisodes.value = result
        }
    }
}