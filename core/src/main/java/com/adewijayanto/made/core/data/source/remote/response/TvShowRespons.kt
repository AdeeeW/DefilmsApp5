package com.adewijayanto.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowRespons(
    @SerializedName("results") val results: List<ResultsTvShowItem>
)

data class ResultsTvShowItem(
    @SerializedName("id") val id: String,
    @SerializedName("first_air_date") val first_air_date: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("original_name") val original_name: String,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("name") val name: String,
    @SerializedName("vote_count") val vote_count: Int,
)