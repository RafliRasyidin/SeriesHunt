package com.rasyidin.serieshunt.core.data.source.remote.network

import com.rasyidin.serieshunt.BuildConfig.MOVIEDB_API_KEY
import com.rasyidin.serieshunt.core.data.source.remote.response.CreditsResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.TvItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("tv/airing_today")
    suspend fun getAiringToday(
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY,
        @Query("page") page: Int = 1
    ): TvResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAir(
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY,
        @Query("page") page: Int = 1
    ): TvResponse

    @GET("tv/popular")
    suspend fun getPopular(
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY,
        @Query("page") page: Int = 1
    ): TvResponse

    @GET("tv/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY,
        @Query("page") page: Int = 1
    ): TvResponse

    @GET("tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Query("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY
    ): TvItemResponse

    @GET("search/tv")
    suspend fun searchTvShow(
        @Query("query") querySearch: String,
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY,
        @Query("page") page: Int = 1
    ): TvResponse

    @GET("tv/{tv_id}/credits")
    suspend fun getCredits(
        @Query("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = MOVIEDB_API_KEY
    ): CreditsResponse
}