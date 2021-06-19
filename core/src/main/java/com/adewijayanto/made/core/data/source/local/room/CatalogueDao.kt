package com.adewijayanto.made.core.data.source.local.room

import androidx.room.*
import com.adewijayanto.made.core.data.source.local.entity.MovieCatalogueEntity
import com.adewijayanto.made.core.data.source.local.entity.TvShowCatalogueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {
    @Query("SELECT * FROM movie_entity")
    fun getAllMovie(): Flow<List<MovieCatalogueEntity>>
    @Query("SELECT * FROM tv_show_entity")
    fun getAllTvShow(): Flow<List<TvShowCatalogueEntity>>

    @Query("SELECT * FROM movie_entity WHERE favorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieCatalogueEntity>>
    @Query("SELECT * FROM tv_show_entity WHERE favorite = 1")
    fun getFavoriteTvShow(): Flow<List<TvShowCatalogueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovie(movie: List<MovieCatalogueEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTvShow(tvshow: List<TvShowCatalogueEntity>)

    @Update
    fun updateMovie(movie: MovieCatalogueEntity)
    @Update
    fun updateTvShow(tvshow: TvShowCatalogueEntity)
}