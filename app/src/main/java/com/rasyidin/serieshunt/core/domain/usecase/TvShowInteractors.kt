package com.rasyidin.serieshunt.core.domain.usecase

import com.rasyidin.serieshunt.core.data.Resource
import com.rasyidin.serieshunt.core.domain.model.TvShow
import com.rasyidin.serieshunt.core.domain.repository.ITvShowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowInteractors @Inject constructor(private val tvShowRepository: ITvShowRepository) : ITvShowUseCase{
    override fun getAiringToday(): Flow<Resource<List<TvShow>>> {
        return tvShowRepository.getAiringToday()
    }

    override fun getOnTheAir(): Flow<Resource<List<TvShow>>> {
        return tvShowRepository.getOnTheAir()
    }

    override fun getPopular(): Flow<Resource<List<TvShow>>> {
        return tvShowRepository.getPopular()
    }

    override fun getTopRated(): Flow<Resource<List<TvShow>>> {
        return tvShowRepository.getTopRated()
    }

    override fun getDetail(idTv: Int): Flow<Resource<TvShow>> {
        return tvShowRepository.getDetail(idTv)
    }

    override fun searchTvShow(querySearch: String): Flow<Resource<List<TvShow>>> {
        return tvShowRepository.searchTvShow(querySearch)
    }
}