package com.adewijayanto.made.core.utils

import com.adewijayanto.made.core.data.source.local.entity.MovieCatalogueEntity
import com.adewijayanto.made.core.data.source.local.entity.TvShowCatalogueEntity
import com.adewijayanto.made.core.data.source.remote.response.ResultsMovieItem
import com.adewijayanto.made.core.data.source.remote.response.ResultsTvShowItem
import com.adewijayanto.made.core.domain.model.Movie
import com.adewijayanto.made.core.domain.model.TvShow

object MapperHelper {
    fun mapResponsesMovieToEntities(input: List<ResultsMovieItem>): List<MovieCatalogueEntity> {
        val movieList = ArrayList<MovieCatalogueEntity>()

        input.map { movie ->
            val movies = MovieCatalogueEntity(
                id = movie.id,
                overview = movie.overview,
                title = movie.title,
                poster_path = movie.poster_path,
                backdrop_path = movie.backdrop_path,
                release_date = movie.release_date,
                vote_average = movie.vote_average / 2,
                favorite = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapResponsesTvShowToEntities(input: List<ResultsTvShowItem>): List<TvShowCatalogueEntity> {
        val tvShowList = ArrayList<TvShowCatalogueEntity>()

        input.map { tvshow ->
            val tvshows = TvShowCatalogueEntity(
                id = tvshow.id,
                first_air_date = tvshow.first_air_date,
                overview = tvshow.overview,
                name = tvshow.name,
                poster_path = tvshow.poster_path,
                backdrop_path = tvshow.backdrop_path,
                vote_average = tvshow.vote_average / 2,
                favorite = false
            )
            tvShowList.add(tvshows)
        }
        return tvShowList
    }

    fun mapEntitiesMovieToDomain(input: List<MovieCatalogueEntity>): List<Movie> =
        input.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                poster_path = movie.poster_path,
                backdrop_path = movie.backdrop_path,
                release_date = movie.release_date,
                vote_average = movie.vote_average,
                favorite = movie.favorite
            )
        }

    fun mapEntitiesTvShowToDomain(input: List<TvShowCatalogueEntity>): List<TvShow> =
        input.map { tvshow ->
            TvShow(
                id = tvshow.id,
                name = tvshow.name,
                overview = tvshow.overview,
                first_air_date = tvshow.first_air_date,
                poster_path = tvshow.poster_path,
                backdrop_path = tvshow.backdrop_path,
                vote_average = tvshow.vote_average,
                favorite = tvshow.favorite
            )
        }

    fun mapDomainToEntityMovie(input: Movie): MovieCatalogueEntity = MovieCatalogueEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        poster_path = input.poster_path,
        backdrop_path = input.backdrop_path,
        release_date = input.release_date,
        vote_average = input.vote_average,
        favorite = input.favorite
    )

    fun mapDomainToEntityTvShow(input: TvShow): TvShowCatalogueEntity = TvShowCatalogueEntity(
        id = input.id,
        name = input.name,
        overview = input.overview,
        first_air_date = input.first_air_date,
        poster_path = input.poster_path,
        backdrop_path = input.backdrop_path,
        vote_average = input.vote_average,
        favorite = input.favorite
    )
}