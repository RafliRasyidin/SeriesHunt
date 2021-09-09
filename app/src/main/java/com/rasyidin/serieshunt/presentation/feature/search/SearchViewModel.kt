package com.rasyidin.serieshunt.presentation.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rasyidin.serieshunt.core.domain.usecase.ITvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: ITvShowUseCase) : ViewModel() {

    val searchResults = useCase.searchResults
        .debounce(1000)
        .distinctUntilChanged()
        .asLiveData(viewModelScope.coroutineContext)

    fun searchTv(query: String) = viewModelScope.launch {
        useCase.searchTvShow(query)
    }

    /*val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchTvShow = queryChannel.asFlow()
        .debounce(1000)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }.mapLatest { query ->
            useCase.searchTvShow(query).asLiveData(viewModelScope.coroutineContext)
        }.asLiveData(viewModelScope.coroutineContext)*/
}