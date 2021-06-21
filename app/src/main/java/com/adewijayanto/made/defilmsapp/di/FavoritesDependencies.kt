@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused")

package com.adewijayanto.made.defilmsapp.di

import com.adewijayanto.made.core.domain.usecase.CatalogueUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoritesDependencies {
    fun catalogueUseCase(): CatalogueUseCase
}