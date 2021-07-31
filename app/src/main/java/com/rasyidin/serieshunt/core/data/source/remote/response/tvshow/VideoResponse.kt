package com.rasyidin.serieshunt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("results")
    val videoItemResponses: List<VideoItemResponse>
)