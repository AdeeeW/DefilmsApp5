package com.adewijayanto.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: String,
    val name: String,
    val overview: String,
    val first_air_date: String,
    val poster_path: String,
    val backdrop_path: String?,
    val vote_average: Float,
    var favorite: Boolean
) : Parcelable
