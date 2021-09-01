package com.rasyidin.serieshunt.core.data.source.remote

import com.rasyidin.serieshunt.core.data.source.remote.network.ApiResponse
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiService
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CastResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CrewResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.EpisodeResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.TvSeasonResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.video.VideoItemResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) :
    IRemoteDataSource, HandleTvShowResponse() {

    override suspend fun getAiringToday(): TvResponse {
        return apiService.getAiringToday()
    }

    override suspend fun getOnTheAir(): TvResponse {
        return apiService.getOnTheAir()
    }

    override suspend fun getPopular(): TvResponse {
        return apiService.getPopular()
    }

    override suspend fun getTopRated(): TvResponse {
        return apiService.getTopRated()
    }

    suspend fun getDetail(tvId: Int): Flow<ApiResponse<TvItemResponse>> {
        val response = apiService.getDetailTvShow(tvId)
        isIdEqualsToZero = response.id == 0
        return handleDetailResponse(response)
    }

    suspend fun searchTvShow(querySearch: String): Flow<ApiResponse<List<TvItemResponse>>> {
        response = apiService.searchTvShow(querySearch)
        return handleResponse(response)
    }

    suspend fun getCast(tvId: Int): Flow<ApiResponse<List<CastResponse>>> {
        creditsResponse = apiService.getCredits(tvId)
        return handleCastResponse(creditsResponse)
    }

    suspend fun getCrew(tvId: Int): Flow<ApiResponse<List<CrewResponse>>> {
        creditsResponse = apiService.getCredits(tvId)
        return handleCrewResponse(creditsResponse)
    }

    suspend fun getVideo(tvId: Int): Flow<ApiResponse<List<VideoItemResponse>>> {
        val response = apiService.getVideos(tvId)
        return handleVideoResponse(response)
    }

    suspend fun getTvSeasons(tvId: Int, seasonNumber: Int): Flow<ApiResponse<List<EpisodeResponse>>> {
        val response = apiService.getTvSeasons(tvId, seasonNumber)
        return handleTvSeasonsResponse(response)
    }

}