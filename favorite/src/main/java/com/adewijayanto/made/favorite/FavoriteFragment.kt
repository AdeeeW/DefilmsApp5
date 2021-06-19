package com.adewijayanto.made.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.adewijayanto.made.favorite.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {
    private var bindingFav: FragmentFavoriteBinding? = null

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFav = FragmentFavoriteBinding.inflate(inflater, container, false)
        return bindingFav?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(activity as AppCompatActivity)
        bindingFav?.apply {
            viewpager.adapter = sectionPagerAdapter
            TabLayoutMediator(tabLayout, viewpager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingFav = null
    }
}