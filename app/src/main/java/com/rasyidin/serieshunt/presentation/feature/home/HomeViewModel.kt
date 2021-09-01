package com.rasyidin.serieshunt.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.serieshunt.core.domain.usecase.ITvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val tvShowUseCase: ITvShowUseCase) : ViewModel() {

    val getAiringToday = tvShowUseCase.listAiringToday.asLiveData(viewModelScope.coroutineContext)

    val getOnTheAir = tvShowUseCase.listOnTheAir.asLiveData(viewModelScope.coroutineContext)

    val getPopular = tvShowUseCase.listPopular.asLiveData(viewModelScope.coroutineContext)

    val getTopRated = tvShowUseCase.listTopRated.asLiveData(viewModelScope.coroutineContext)

    fun getAiringToday() = viewModelScope.launch {
        tvShowUseCase.getAiringToday()
    }

    fun getOnTheAir() = viewModelScope.launch {
        tvShowUseCase.getOnTheAir()
    }

    fun getPopular() = viewModelScope.launch {
        tvShowUseCase.getPopular()
    }

    fun getTopRated() = viewModelScope.launch {
        tvShowUseCase.getTopRated()
    }
}