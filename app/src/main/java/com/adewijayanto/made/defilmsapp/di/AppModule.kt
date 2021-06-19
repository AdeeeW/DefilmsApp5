package com.adewijayanto.made.defilmsapp.di

import com.adewijayanto.made.core.data.CatalogRepository
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import com.adewijayanto.made.core.domain.usecase.CatalogueUseCaseImplement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieKuUseCase(catalogRepository: CatalogRepository): CatalogueUseCase =
        CatalogueUseCaseImplement(catalogRepository)

}