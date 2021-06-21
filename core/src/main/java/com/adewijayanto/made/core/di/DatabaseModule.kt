@file:Suppress("PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType",
    "PublicApiImplicitType", "PublicApiImplicitType", "PublicApiImplicitType"
)

package com.adewijayanto.made.core.di

import android.content.Context
import androidx.room.Room
import com.adewijayanto.made.core.data.source.local.room.CatalogueDao
import com.adewijayanto.made.core.data.source.local.room.CatalogueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val passphrase: ByteArray = SQLiteDatabase.getBytes("defilms5".toCharArray())
    val factory = SupportFactory(passphrase)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CatalogueDatabase =
        Room.databaseBuilder(
            context,
            CatalogueDatabase::class.java, "Catalogue.db"
        ).allowMainThreadQueries().openHelperFactory(factory).build()

    @Provides
    fun provideCatalogDatabaseDao(database: CatalogueDatabase): CatalogueDao =
        database.catalogueDao()

}