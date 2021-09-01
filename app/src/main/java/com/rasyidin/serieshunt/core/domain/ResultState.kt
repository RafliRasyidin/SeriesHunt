package com.rasyidin.serieshunt.core.domain

sealed class ResultState<T : Any> {
    class Loading<T : Any> : ResultState<T>()
    class Idle<T : Any> : ResultState<T>()
    data class Success<T : Any>(val data: T) : ResultState<T>()
    data class Error<T : Any>(val throwable: Throwable) : ResultState<T>()
}
