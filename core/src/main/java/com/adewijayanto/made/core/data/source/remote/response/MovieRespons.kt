package com.adewijayanto.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieRespons(
    @SerializedName("results") val results: List<ResultsMovieItem>
)

data class ResultsMovieItem(
    @SerializedName("id") val id: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("original_title") val original_title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("vote_average") val vote_average: Float
)