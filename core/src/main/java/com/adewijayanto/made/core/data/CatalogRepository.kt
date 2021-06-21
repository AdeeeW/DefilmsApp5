package com.adewijayanto.made.core.data

import com.adewijayanto.made.core.data.source.local.LocalDataSource
import com.adewijayanto.made.core.data.source.remote.RemoteDataSource
import com.adewijayanto.made.core.domain.model.Movie
import com.adewijayanto.made.core.domain.model.TvShow
import com.adewijayanto.made.core.domain.repository.ICatalogueRepository
import com.adewijayanto.made.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import com.adewijayanto.made.core.data.source.remote.network.ApiResponse
import com.adewijayanto.made.core.data.source.remote.response.ResultsMovieItem
import com.adewijayanto.made.core.data.source.remote.response.ResultsTvShowItem
import com.adewijayanto.made.core.utils.MapperHelper
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class CatalogRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {
    override fun getMovie(): Flow<Resources<List<Movie>>> {
        return object : NetworkResourceBound<List<Movie>, List<ResultsMovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    MapperHelper.mapEntitiesMovieToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsMovieItem>>> {
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<ResultsMovieItem>) {
                val movieList = MapperHelper.mapResponsesMovieToEntities(data)
                localDataSource.insertAllMovie(movieList)
            }
        }.asFlow() as Flow<Resources<List<Movie>>>
    }

    override fun getTvShow(): Flow<Resources<List<TvShow>>> {
        return object : NetworkResourceBound<List<TvShow>, List<ResultsTvShowItem>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvSHow().map {
                    MapperHelper.mapEntitiesTvShowToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsTvShowItem>>> {
                return remoteDataSource.getAllTvShow()
            }

            override suspend fun saveCallResult(data: List<ResultsTvShowItem>) {
                val tvShowList = MapperHelper.mapResponsesTvShowToEntities(data)
                localDataSource.insertAllTvSHow(tvShowList)
            }
        }.asFlow() as Flow<Resources<List<TvShow>>>
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            MapperHelper.mapEntitiesMovieToDomain(it)
        }
    }

    override fun getFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvSHow().map {
            MapperHelper.mapEntitiesTvShowToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntities = MapperHelper.mapDomainToEntityMovie(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovies(movieEntities, state)
        }
    }

    override fun setFavoriteTvShow(tvshow: TvShow, state: Boolean) {
        val tvshowEntities = MapperHelper.mapDomainToEntityTvShow(tvshow)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvshowEntities, state)
        }
    }

}