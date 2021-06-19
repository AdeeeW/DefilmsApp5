package com.adewijayanto.made.core.domain.usecase

import com.adewijayanto.made.core.data.Resources
import com.adewijayanto.made.core.domain.model.Movie
import com.adewijayanto.made.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface CatalogueUseCase {
    fun getMovie(): Flow<Resources<List<Movie>>>
    fun getTvShow(): Flow<Resources<List<TvShow>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun getFavoriteTvShow(): Flow<List<TvShow>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
    fun setFavoriteTvShow(tvshow: TvShow, state: Boolean)
}