package com.rasyidin.serieshunt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class SeasonResponse(

    @SerializedName("id")
    val idSeason: Int = 0,

    @SerializedName("season_number")
    val seasonNumber: Int = 0
)