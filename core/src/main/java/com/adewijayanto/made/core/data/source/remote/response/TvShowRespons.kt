package com.adewijayanto.made.core.data.source.remote.response

data class TvShowRespons(
    val results: List<ResultsTvShowItem>
)

data class ResultsTvShowItem(
    val id: String,
    val first_air_date: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val original_name: String,
    val vote_average: Float,
    val name: String,
    val vote_count: Int,
)