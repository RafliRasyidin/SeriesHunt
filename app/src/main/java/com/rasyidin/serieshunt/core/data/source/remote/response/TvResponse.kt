package com.rasyidin.serieshunt.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<TvItemResponse>,
)