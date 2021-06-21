package com.adewijayanto.made.defilmsapp.detail

import androidx.lifecycle.ViewModel
import com.adewijayanto.made.core.domain.model.Movie
import com.adewijayanto.made.core.domain.model.TvShow
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val catalogueUseCase: CatalogueUseCase) :
    ViewModel() {
    fun setFavoriteMovies(movies: Movie, state: Boolean) =
        catalogueUseCase.setFavoriteMovie(movies, state)

    fun setFavoriteTvShow(tvshow: TvShow, state: Boolean) =
        catalogueUseCase.setFavoriteTvShow(tvshow, state)
}