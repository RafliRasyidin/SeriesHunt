package com.rasyidin.serieshunt.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvItemResponse(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("backdrop_path")
    val backdropPath: String? = "",

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @SerializedName("first_air_date")
    val firstAirDate: String? = "",

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("last_air_date")
    val lastAirDate: String? = "",

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int = 0,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int = 0,

    @SerializedName("overview")
    val overview: String? = "",

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("poster_path")
    val posterPath: String? = "",

    @SerializedName("seasons")
    val seasonResponses: List<SeasonResponse>,

    @SerializedName("status")
    val status: String? = "",

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    val voteCount: Int = 0,

    @SerializedName("homepage")
    val homepage: String? = "",
)