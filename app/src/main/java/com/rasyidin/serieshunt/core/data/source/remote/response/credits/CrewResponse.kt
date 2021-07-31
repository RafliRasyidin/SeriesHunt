package com.rasyidin.serieshunt.core.data.source.remote.response.credits

import com.google.gson.annotations.SerializedName

data class CrewResponse(
    @SerializedName("job")
    val job: String? = "",

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("profile_path")
    val profilePath: String? = ""
)