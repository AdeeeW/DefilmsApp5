package com.adewijayanto.made.defilmsapp.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase): ViewModel() {
    val movies = catalogueUseCase.getMovie().asLiveData()
}