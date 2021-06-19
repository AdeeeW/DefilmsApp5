package com.adewijayanto.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String?,
    val release_date: String,
    val vote_average: Float,
    var favorite: Boolean
) : Parcelable