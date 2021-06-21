package com.adewijayanto.made.favorite.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adewijayanto.made.core.ui.ListTvShowAdapter
import com.adewijayanto.made.defilmsapp.detail.DetailActivity
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.DATA_TVSHOW
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.NAME
import com.adewijayanto.made.defilmsapp.di.FavoritesDependencies
import com.adewijayanto.made.favorite.databinding.FragmentFavTvshowBinding
import com.adewijayanto.made.favorite.di.DaggerFavoriteComponent
import com.adewijayanto.made.favorite.vo.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavTvshowFragment : Fragment() {
    private var bindingFavTvShow: FragmentFavTvshowBinding? = null

    @Inject
    lateinit var factoryViewModel: ViewModelFactory
    private val favoritesTvShowViewModel: FavTvShowViewModel by viewModels { factoryViewModel }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFavTvShow = FragmentFavTvshowBinding.inflate(inflater, container, false)
        return bindingFavTvShow?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoritesDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val favoriteTvShowAdapter = ListTvShowAdapter()
            favoriteTvShowAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DATA_TVSHOW, it)
                intent.putExtra(NAME, "tv_show")
                startActivity(intent)
            }

            favoritesTvShowViewModel.favTvShow.observe(viewLifecycleOwner) { listTvshow ->
                favoriteTvShowAdapter.setData(listTvshow)
                if (listTvshow.isEmpty()) {
                    bindingFavTvShow?.apply {
                        imgErrorFav.visibility = View.VISIBLE
                        textErrorFav.visibility = View.VISIBLE
                    }
                }
            }

            bindingFavTvShow?.let {
                with(it.rvTvshow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = favoriteTvShowAdapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingFavTvShow = null
    }
}