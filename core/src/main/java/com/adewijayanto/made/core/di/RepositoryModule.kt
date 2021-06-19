package com.adewijayanto.made.core.di

import com.adewijayanto.made.core.data.CatalogRepository
import com.adewijayanto.made.core.domain.repository.ICatalogueRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(catalogRepository: CatalogRepository): ICatalogueRepository

}