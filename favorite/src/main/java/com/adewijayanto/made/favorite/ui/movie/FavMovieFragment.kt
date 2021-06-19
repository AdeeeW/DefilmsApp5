package com.adewijayanto.made.favorite.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adewijayanto.made.core.ui.ListMovieAdapter
import com.adewijayanto.made.defilmsapp.detail.DetailActivity
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.DATA_MOVIE
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.NAME
import com.adewijayanto.made.defilmsapp.di.FavoritesDependencies
import com.adewijayanto.made.favorite.databinding.FragmentFavMovieBinding
import com.adewijayanto.made.favorite.di.DaggerFavoriteComponent
import com.adewijayanto.made.favorite.vo.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavMovieFragment : Fragment() {
    private var bindingFavMovie: FragmentFavMovieBinding? = null

    @Inject
    lateinit var factoryViewModel: ViewModelFactory
    private val favoritesMovieViewModel: FavMovieViewModel by viewModels {factoryViewModel}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFavMovie = FragmentFavMovieBinding.inflate(inflater, container, false)
        return bindingFavMovie?.root
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

        if (activity != null){

            val favoriteMovieAdapter = ListMovieAdapter()
            favoriteMovieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DATA_MOVIE, it)
                intent.putExtra(NAME, "movie")
                startActivity(intent)
            }

            favoritesMovieViewModel.favMovies.observe(viewLifecycleOwner) { listMovie ->
                favoriteMovieAdapter.setData(listMovie)
                if (listMovie.isEmpty()) {
                    bindingFavMovie?.apply {
                        imgErrorFav.visibility = View.VISIBLE
                        textErrorFav.visibility = View.VISIBLE
                    }
                }
            }

            bindingFavMovie?.let {
                with(it.rvMovie){
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = favoriteMovieAdapter
                }
            }
        }
    }
}