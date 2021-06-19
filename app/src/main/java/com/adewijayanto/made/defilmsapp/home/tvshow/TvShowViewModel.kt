package com.adewijayanto.made.defilmsapp.home.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(catalogueUseCase: CatalogueUseCase): ViewModel() {
    val tvshow = catalogueUseCase.getTvShow().asLiveData()
}