package com.rasyidin.serieshunt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page")
    val page: Int = 1,

    @SerializedName("results")
    val results: List<TvItemResponse>,
)