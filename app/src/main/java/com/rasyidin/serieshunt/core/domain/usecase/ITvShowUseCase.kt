package com.rasyidin.serieshunt.core.domain.usecase

import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowUseCase {

    fun getAiringToday(): Flow<Resource<List<TvShow>>>

    fun getOnTheAir(): Flow<Resource<List<TvShow>>>

    fun getPopular(): Flow<Resource<List<TvShow>>>

    fun getTopRated(): Flow<Resource<List<TvShow>>>

    fun getDetail(idTv: Int): Flow<Resource<TvShow>>

    fun searchTvShow(querySearch: String): Flow<Resource<List<TvShow>>>
}