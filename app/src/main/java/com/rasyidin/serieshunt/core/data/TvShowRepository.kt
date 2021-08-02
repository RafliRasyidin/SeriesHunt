package com.rasyidin.serieshunt.core.data

import com.rasyidin.serieshunt.core.data.source.remote.RemoteDataSource
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiResponse
import com.rasyidin.serieshunt.core.domain.model.Cast
import com.rasyidin.serieshunt.core.domain.model.Crew
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.domain.model.VideoTrailer
import com.rasyidin.serieshunt.core.domain.repository.ITvShowRepository
import com.rasyidin.serieshunt.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class TvShowRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ITvShowRepository {

    override fun getAiringToday(): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getAiringToday().collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.toListTvShow()))
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    is ApiResponse.Empty -> emit(Resource.Success<List<TvShow>>(emptyList()))
                }
            }
        } as Flow<Resource<List<TvShow>>>
    }

    override fun getOnTheAir(): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getOnTheAir().collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.toListTvShow()))
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    is ApiResponse.Empty -> emit(Resource.Success<List<TvShow>>(emptyList()))
                }
            }
        } as Flow<Resource<List<TvShow>>>
    }

    override fun getPopular(): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getPopular().collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.toListTvShow()))
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    is ApiResponse.Empty -> emit(Resource.Success<List<TvShow>>(emptyList()))
                }
            }
        } as Flow<Resource<List<TvShow>>>
    }

    override fun getTopRated(): Flow<Resource<List<TvShow>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getTopRated().collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> emit(Resource.Success(apiResponse.data.toListTvShow()))
                    is ApiResponse.Error -> emit(Resource.Error(null, apiResponse.message))
                    is ApiResponse.Empty -> emit(Resource.Success<List<TvShow>>(emptyList()))
                }
            }
        } as Flow<Resource<List<TvShow>>>
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
}