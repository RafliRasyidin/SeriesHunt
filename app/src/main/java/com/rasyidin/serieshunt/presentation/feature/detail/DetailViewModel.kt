package com.rasyidin.serieshunt.presentation.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.serieshunt.core.domain.usecase.ITvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: ITvShowUseCase): ViewModel() {

    fun getCast(tvId: Int) = useCase.getCast(tvId).asLiveData(viewModelScope.coroutineContext)

    fun getCrew(tvId: Int) = useCase.getCast(tvId).asLiveData(viewModelScope.coroutineContext)

    fun getDetail(tvId: Int) = useCase.getDetail(tvId).asLiveData(viewModelScope.coroutineContext)

    fun getVideos(tvId: Int) = useCase.getVideos(tvId).asLiveData(viewModelScope.coroutineContext)

}