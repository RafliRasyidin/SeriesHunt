package com.rasyidin.serieshunt.core.data.source.remote

import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvResponse

interface IRemoteDataSource {

    suspend fun getAiringToday(): TvResponse

    suspend fun getOnTheAir(): TvResponse

    suspend fun getPopular(): TvResponse

    suspend fun getTopRated(): TvResponse
}