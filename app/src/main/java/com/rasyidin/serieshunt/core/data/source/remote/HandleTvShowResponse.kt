package com.rasyidin.serieshunt.core.data.source.remote

import android.util.Log
import com.rasyidin.serieshunt.core.data.source.remote.network.ApiResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CastResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CreditsResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.credits.CrewResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.EpisodeResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvseason.TvSeasonResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.tvshow.TvResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.video.VideoItemResponse
import com.rasyidin.serieshunt.core.data.source.remote.response.video.VideoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class HandleTvShowResponse {

    protected var isIdEqualsToZero: Boolean = true

    protected lateinit var response: TvResponse

    protected lateinit var creditsResponse: CreditsResponse

    protected fun handleResponse(response: TvResponse): Flow<ApiResponse<List<TvItemResponse>>> {
        return flow {
            val data = response.results
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    protected fun handleDetailResponse(response: TvItemResponse): Flow<ApiResponse<TvItemResponse>> {
        return flow {
            if (!isIdEqualsToZero) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    protected fun handleCastResponse(response: CreditsResponse): Flow<ApiResponse<List<CastResponse>>> {
        return flow {
            val data = response.castResponse
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    protected fun handleCrewResponse(response: CreditsResponse): Flow<ApiResponse<List<CrewResponse>>> {
        return flow {
            val data = response.crewResponse
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
        }.flowOn(Dispatchers.IO)
    }

    protected fun handleVideoResponse(response: VideoResponse): Flow<ApiResponse<List<VideoItemResponse>>> {
        return flow {
            val data = response.videoItemResponses
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            e.message?.let {
                emit(ApiResponse.Error(it))
            }
        }.flowOn(Dispatchers.IO)
    }

    protected fun handleTvSeasonsResponse(response: TvSeasonResponse): Flow<ApiResponse<List<EpisodeResponse>>> {
        return flow {
            val data = response.episodeResponses
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.message.toString()))
            Log.e("EpisodesFragment", "${e.message}")
        }.flowOn(Dispatchers.IO)
    }
}