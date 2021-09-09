package com.rasyidin.serieshunt.core.data.source.remote

import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CreditsResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.TvSeasonResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.video.VideoResponse

interface IRemoteDataSource {

    suspend fun getAiringToday(): TvResponse

    suspend fun getOnTheAir(): TvResponse

    suspend fun getPopular(): TvResponse

    suspend fun getTopRated(): TvResponse

    suspend fun getDetail(tvId: Int): TvItemResponse

    suspend fun getCredits(tvId: Int): CreditsResponse

    suspend fun getVideos(tvId: Int): VideoResponse

    suspend fun getTvSeasons(tvId: Int, seasonNumber: Int): TvSeasonResponse

    suspend fun searchTvShow(query: String): TvResponse
}