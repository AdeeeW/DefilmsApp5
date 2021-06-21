package com.adewijayanto.made.favorite.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavTvShowViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val favTvShow = catalogueUseCase.getFavoriteTvShow().asLiveData()
}