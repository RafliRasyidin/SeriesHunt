package com.rasyidin.serieshunt.core.data.source.remote

import com.rasyidin.serieshunt.core.data.source.remote.network.ApiResponse
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiService
import com.rasyidin.serieshunt.core.data.source.remote.response.TvItemResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) :
    HandleTvShowResponse() {

    suspend fun getAiringToday(): Flow<ApiResponse<List<TvItemResponse>>> {
        response = apiService.getAiringToday()
        return responseHandle(response)
    }

    suspend fun getOnTheAir(): Flow<ApiResponse<List<TvItemResponse>>> {
        response = apiService.getOnTheAir()
        return responseHandle(response)
    }

    suspend fun getPopular(): Flow<ApiResponse<List<TvItemResponse>>> {
        response = apiService.getPopular()
        return responseHandle(response)
    }

    suspend fun getTopRated(): Flow<ApiResponse<List<TvItemResponse>>> {
        response = apiService.getTopRated()
        return responseHandle(response)
    }

    suspend fun getDetail(tvId: Int): Flow<ApiResponse<TvItemResponse>> {
        val response = apiService.getDetailTvShow(tvId)
        isIdEqualsToZero = response.id == 0
        return detailResponseHandle(response)
    }

    suspend fun searchTvShow(querySearch: String): Flow<ApiResponse<List<TvItemResponse>>> {
        response = apiService.searchTvShow(querySearch)
        return responseHandle(response)
    }


}