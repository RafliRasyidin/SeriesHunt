package com.rasyidin.serieshunt.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvItemResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,

    @SerializedName("firstAirDate")
    val firstAirDate: String,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("seasons")
    val seasonResponses: List<SeasonResponse>,

    @SerializedName("status")
    val status: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)