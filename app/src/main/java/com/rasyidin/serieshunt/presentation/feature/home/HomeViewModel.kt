package com.rasyidin.serieshunt.presentation.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.serieshunt.core.domain.usecase.ITvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(tvShowUseCase: ITvShowUseCase) : ViewModel(){

    val getAiringToday = tvShowUseCase.getAiringToday().asLiveData(viewModelScope.coroutineContext)

    val getOnTheAir = tvShowUseCase.getOnTheAir().asLiveData(viewModelScope.coroutineContext)

    val getPopular = tvShowUseCase.getPopular().asLiveData(viewModelScope.coroutineContext)

    val getTopRated = tvShowUseCase.getTopRated().asLiveData(viewModelScope.coroutineContext)

}