@file:Suppress("PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType"
)

package com.adewijayanto.made.core.domain.usecase

import com.adewijayanto.made.core.domain.model.Movie
import com.adewijayanto.made.core.domain.model.TvShow
import com.adewijayanto.made.core.domain.repository.ICatalogueRepository
import javax.inject.Inject

class CatalogueUseCaseImplement @Inject constructor(private val ICatalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {
    override fun getMovie() = ICatalogueRepository.getMovie()
    override fun getTvShow() = ICatalogueRepository.getTvShow()
    override fun getFavoriteMovie() = ICatalogueRepository.getFavoriteMovie()
    override fun getFavoriteTvShow() = ICatalogueRepository.getFavoriteTvShow()
    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return ICatalogueRepository.setFavoriteMovie(movie, state)
    }

    override fun setFavoriteTvShow(tvshow: TvShow, state: Boolean) {
        return ICatalogueRepository.setFavoriteTvShow(tvshow, state)
    }
}
