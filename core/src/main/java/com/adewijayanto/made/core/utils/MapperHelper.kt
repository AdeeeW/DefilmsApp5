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

        input.map { (id, overview, _, _, title, poster_path, backdrop_path, release_date, vote_average) ->
            val movies = MovieCatalogueEntity(
                id = id,
                overview = overview,
                title = title,
                poster_path = poster_path,
                backdrop_path = backdrop_path,
                release_date = release_date,
                vote_average = vote_average / 2,
                favorite = false
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapResponsesTvShowToEntities(input: List<ResultsTvShowItem>): List<TvShowCatalogueEntity> {
        val tvShowList = ArrayList<TvShowCatalogueEntity>()

        input.map { (id, first_air_date, overview, poster_path, backdrop_path, _, vote_average, name) ->
            val tvshows = TvShowCatalogueEntity(
                id = id,
                first_air_date = first_air_date,
                overview = overview,
                name = name,
                poster_path = poster_path,
                backdrop_path = backdrop_path,
                vote_average = vote_average / 2,
                favorite = false
            )
            tvShowList.add(tvshows)
        }
        return tvShowList
    }

    fun mapEntitiesMovieToDomain(input: List<MovieCatalogueEntity>): List<Movie> =
        input.map { (id, overview, title, poster_path, backdrop_path, release_date, vote_average, favorite) ->
            Movie(
                id = id,
                title = title,
                overview = overview,
                poster_path = poster_path,
                backdrop_path = backdrop_path,
                release_date = release_date,
                vote_average = vote_average,
                favorite = favorite
            )
        }

    fun mapEntitiesTvShowToDomain(input: List<TvShowCatalogueEntity>): List<TvShow> =
        input.map { (id, first_air_date, overview, name, poster_path, backdrop_path, vote_average, favorite) ->
            TvShow(
                id = id,
                name = name,
                overview = overview,
                first_air_date = first_air_date,
                poster_path = poster_path,
                backdrop_path = backdrop_path,
                vote_average = vote_average,
                favorite = favorite
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