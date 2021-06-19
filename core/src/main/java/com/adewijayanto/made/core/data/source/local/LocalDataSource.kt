package com.adewijayanto.made.core.data.source.local

import com.adewijayanto.made.core.data.source.local.entity.MovieCatalogueEntity
import com.adewijayanto.made.core.data.source.local.entity.TvShowCatalogueEntity
import com.adewijayanto.made.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val catalogueDao: CatalogueDao) {
    fun getAllMovie(): Flow<List<MovieCatalogueEntity>> = catalogueDao.getAllMovie()
    fun getAllTvSHow(): Flow<List<TvShowCatalogueEntity>> = catalogueDao.getAllTvShow()
    fun getFavoriteMovie(): Flow<List<MovieCatalogueEntity>> = catalogueDao.getFavoriteMovie()
    fun getFavoriteTvSHow(): Flow<List<TvShowCatalogueEntity>> = catalogueDao.getFavoriteTvShow()
    fun insertAllMovie(movie: List<MovieCatalogueEntity>) = catalogueDao.insertAllMovie(movie)
    fun insertAllTvSHow(tvshow: List<TvShowCatalogueEntity>) = catalogueDao.insertAllTvShow(tvshow)
    fun setFavoriteMovies(movieEntities: MovieCatalogueEntity, newState: Boolean){
        movieEntities.favorite = newState
        catalogueDao.updateMovie(movieEntities)
    }
    fun setFavoriteTvShow(tvshowEntities: TvShowCatalogueEntity, newState: Boolean){
        tvshowEntities.favorite = newState
        catalogueDao.updateTvShow(tvshowEntities)
    }
}