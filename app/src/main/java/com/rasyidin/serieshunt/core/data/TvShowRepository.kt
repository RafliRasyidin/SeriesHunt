package com.rasyidin.serieshunt.core.data

import com.rasyidin.serieshunt.core.data.source.remote.RemoteDataSource
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiResponse
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.domain.repository.ITvShowRepository
import com.rasyidin.serieshunt.core.utils.toListTvShow
import com.rasyidin.serieshunt.core.utils.toTvShow
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

    override fun getDetail(idTv: Int): Flow<Resource<TvShow>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getDetail(idTv).collect { apiResponse ->
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
}