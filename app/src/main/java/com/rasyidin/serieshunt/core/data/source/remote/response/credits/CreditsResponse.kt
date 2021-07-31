package com.rasyidin.serieshunt.core.data.source.remote.response.credits

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val castResponse: List<CastResponse>,

    @SerializedName("crew")
    val crewResponse: List<CrewResponse>,
)