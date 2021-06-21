package com.adewijayanto.made.favorite.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavMovieViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val favMovies = catalogueUseCase.getFavoriteMovie().asLiveData()
}