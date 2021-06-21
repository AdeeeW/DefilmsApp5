@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused")

package com.adewijayanto.made.favorite.di

import android.content.Context
import com.adewijayanto.made.defilmsapp.di.FavoritesDependencies
import com.adewijayanto.made.favorite.ui.movie.FavMovieFragment
import com.adewijayanto.made.favorite.ui.tvshow.FavTvshowFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoritesDependencies::class])
interface FavoriteComponent {

    fun inject(favMovieFragment: FavMovieFragment)
    fun inject(favTvshowFragment: FavTvshowFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoritesDependencies: FavoritesDependencies): Builder
        fun build(): FavoriteComponent
    }

}