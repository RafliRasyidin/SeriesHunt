package com.rasyidin.serieshunt.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SeasonResponse(
    @SerializedName("air_date")
    val airDate: String = "",

    @SerializedName("episode_count")
    val episodeCount: Int = 0,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("overview")
    val overview: String = "",

    @SerializedName("poster_path")
    val posterPath: String = "",

    @SerializedName("season_number")
    val seasonNumber: Int = 0
)