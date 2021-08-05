package com.rasyidin.serieshunt.core.data.source.remote.response.tvseason

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("air_date")
    val airDate: String? = "",

    @SerializedName("episode_number")
    val episodeNumber: Int = 0,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("still_path")
    val posterPath: String? = "",
)