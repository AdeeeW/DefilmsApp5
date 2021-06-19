package com.adewijayanto.made.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie_entity")
@Parcelize
data class MovieCatalogueEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "posterPath")
    val poster_path: String,
    @ColumnInfo(name = "backdropPath")
    val backdrop_path: String?,
    @ColumnInfo(name = "releaseDate")
    val release_date: String,
    @ColumnInfo(name = "voteAverage")
    val vote_average: Float,
    @ColumnInfo(name = "favorite")
    var favorite: Boolean
) : Parcelable
