@file:Suppress("UNCHECKED_CAST")

package com.adewijayanto.made.favorite.vo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import com.adewijayanto.made.favorite.ui.movie.FavMovieViewModel
import com.adewijayanto.made.favorite.ui.tvshow.FavTvShowViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val catalogueUseCase: CatalogueUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> {
                FavMovieViewModel(catalogueUseCase) as T
            }
            modelClass.isAssignableFrom(FavTvShowViewModel::class.java) -> {
                FavTvShowViewModel(catalogueUseCase) as T
            }
            else -> throw Throwable("Unkwon ViewModel Class: ${modelClass.name}")
        }
}