package com.adewijayanto.made.core.data.source.remote.response

data class MovieRespons(
    val results: List<ResultsMovieItem>
)

data class ResultsMovieItem(
    val id: String,
    val overview: String,
    val original_title: String,
    val video: Boolean,
    val title: String,
    val poster_path: String,
    val backdrop_path: String,
    val release_date: String,
    val vote_average: Float
)