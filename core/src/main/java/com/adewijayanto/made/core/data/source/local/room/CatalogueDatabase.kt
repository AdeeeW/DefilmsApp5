package com.adewijayanto.made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adewijayanto.made.core.data.source.local.entity.MovieCatalogueEntity
import com.adewijayanto.made.core.data.source.local.entity.TvShowCatalogueEntity

@Database(
    entities = [MovieCatalogueEntity::class, TvShowCatalogueEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CatalogueDatabase : RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao
}