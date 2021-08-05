package com.rasyidin.serieshunt.core.data.source.remote.response.tvseason

import com.google.gson.annotations.SerializedName

data class TvSeasonResponse(
    @SerializedName("episodes")
    val episodeResponses: List<EpisodeResponse>,

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("season_number")
    val seasonNumber: Int = 0
)