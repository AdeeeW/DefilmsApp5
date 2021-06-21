package com.adewijayanto.made.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adewijayanto.made.favorite.ui.movie.FavMovieFragment
import com.adewijayanto.made.favorite.ui.tvshow.FavTvshowFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavMovieFragment()
            1 -> FavTvshowFragment()
            else -> FavTvshowFragment()
        }
    }
}