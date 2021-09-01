package com.rasyidin.serieshunt.core.data.repository

import android.util.Log
import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.data.source.remote.RemoteDataSource
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiResponse
import com.rasyidin.serieshunt.core.domain.ResultState
import com.rasyidin.serieshunt.core.domain.model.*
import com.rasyidin.serieshunt.core.utils.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class TvShowRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ITvShowRepository {

    private val _listAiringToday: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _listOnTheAir: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _listPopular: MutableStateFlow<ResultState<TvResult>> = idle()
    private val _listTopRated: MutableStateFlow<ResultState<TvResult>> = idle()

    override val listAiringToday: StateFlow<ResultState<TvResult>>
        get() = _listAiringToday
    override val listOnTheAir: StateFlow<ResultState<TvResult>>
        get() = _listOnTheAir
    override val listPopular: StateFlow<ResultState<TvResult>>
        get() = _listPopular
    override val listTopRated: StateFlow<ResultState<TvResult>>
        get() = _listTopRated

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

    override fun getDetail(tvId: Int): Flow<Resource<TvShow>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getDetail(tvId).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.toTvShow()))
                    is ApiResponse.Empty -> emit(Resource.Success<TvShow>(null))
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                }
            }
        } as Flow<Resource<TvShow>>
    }

    override fun searchTvShow(querySearch: String): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remoteDataSource.searchTvShow(querySearch).first()) {
                is ApiResponse.Success -> {
                    val result = response.data.toListTvShow()
                    emit(Resource.Success(result))
                }
                is ApiResponse.Empty -> emit(Resource.Success<List<TvShow>>(emptyList()))
                is ApiResponse.Error -> emit(Resource.Error(null, response.message))
            }
        } as Flow<Resource<List<TvShow>>>
    }

    override fun getCast(tvId: Int): Flow<Resource<List<Cast>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getCast(tvId).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val result = apiResponse.data.toListCast()
                        emit(Resource.Success(result))
                    }
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    ApiResponse.Empty -> emit(Resource.Success<List<Cast>>(emptyList()))
                }
            }
        } as Flow<Resource<List<Cast>>>
    }

    override fun getCrew(tvId: Int): Flow<Resource<List<Crew>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getCrew(tvId).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val result = apiResponse.data.toListCrew()
                        emit(Resource.Success(result))
                    }
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    ApiResponse.Empty -> emit(Resource.Success<List<Crew>>(emptyList()))
                }
            }
        } as Flow<Resource<List<Crew>>>
    }

    override fun getVideos(tvId: Int): Flow<Resource<List<VideoTrailer>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getVideo(tvId).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val result = apiResponse.data.toListVideoTrailer()
                        emit(Resource.Success(result))
                    }
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    ApiResponse.Empty -> emit(Resource.Success<List<VideoTrailer>>(emptyList()))
                }
            }
        } as Flow<Resource<List<VideoTrailer>>>
    }

    override fun getTvSeasons(tvId: Int, seasonNumber: Int): Flow<Resource<List<TvEpisode>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getTvSeasons(tvId, seasonNumber).collect { apiResponse ->
                when (apiResponse) {
                    ApiResponse.Empty -> emit(Resource.Success<List<TvEpisode>>(emptyList()))
                    is ApiResponse.Error -> {
                        emit(Resource.Error(null, apiResponse.message))
                        Log.e("EpisodesFragment", apiResponse.message)
                    }
                    is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.toListTvEpisode()))
                }
            }
        } as Flow<Resource<List<TvEpisode>>>
    }
}