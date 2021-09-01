package com.rasyidin.serieshunt.presentation.utils

import com.rasyidin.serieshunt.core.domain.ResultState

fun <T: Any> isLoading(result: ResultState<T>): Boolean {
    return result is ResultState.Loading
}

fun <T: Any> isSuccess(result: ResultState<T>): Boolean {
    return result is ResultState.Success
}

fun <T: Any> isFailure(result: ResultState<T>): Boolean {
    return result is ResultState.Error
}