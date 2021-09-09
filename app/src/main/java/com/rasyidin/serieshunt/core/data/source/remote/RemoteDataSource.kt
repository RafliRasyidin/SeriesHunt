package com.rasyidin.serieshunt.core.data.source.remote

import com.rasyidin.serieshunt.core.data.source.remote.network.ApiService
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CreditsResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.TvSeasonResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.video.VideoResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : IRemoteDataSource{

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

    override suspend fun getDetail(tvId: Int): TvItemResponse {
        return apiService.getDetailTvShow(tvId)
    }

    override suspend fun getCredits(tvId: Int): CreditsResponse {
        return apiService.getCredits(tvId)
    }

    override suspend fun getVideos(tvId: Int): VideoResponse {
        return apiService.getVideos(tvId)
    }

    override suspend fun searchTvShow(query: String): TvResponse {
        return apiService.searchTvShow(query)
    }

    override suspend fun getTvSeasons(tvId: Int, seasonNumber: Int): TvSeasonResponse {
        return apiService.getTvSeasons(tvId, seasonNumber)
    }

}