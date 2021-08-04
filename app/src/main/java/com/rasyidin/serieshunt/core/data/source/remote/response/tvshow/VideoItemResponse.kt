package com.rasyidin.serieshunt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class VideoItemResponse(
    @SerializedName("id")
    val id: String? = "",

    @SerializedName("key")
    val key: String? = "",

    @SerializedName("site")
    val site: String? = "",

    @SerializedName("type")
    val type: String? = ""
)