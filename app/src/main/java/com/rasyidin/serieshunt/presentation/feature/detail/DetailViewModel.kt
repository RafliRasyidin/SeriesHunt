package com.rasyidin.serieshunt.presentation.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.serieshunt.core.domain.usecase.ITvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: ITvShowUseCase) : ViewModel() {

    val listCast = useCase.listCast.asLiveData(viewModelScope.coroutineContext)
    val listCrew = useCase.listCrew.asLiveData(viewModelScope.coroutineContext)
    val tvShow = useCase.tvShow.asLiveData(viewModelScope.coroutineContext)
    val videos = useCase.videos.asLiveData(viewModelScope.coroutineContext)
    val tvEpisodes = useCase.tvEpisodes.asLiveData(viewModelScope.coroutineContext)

    fun getCast(tvId: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCast(tvId)
    }

    fun getCrew(tvId: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getCrew(tvId)
    }

    fun getDetail(tvId: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getDetail(tvId)
    }

    fun getVideos(tvId: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getVideos(tvId)
    }

    fun getTvSeason(tvId: Int, seasonNumber: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getTvSeasons(tvId, seasonNumber)
    }

}