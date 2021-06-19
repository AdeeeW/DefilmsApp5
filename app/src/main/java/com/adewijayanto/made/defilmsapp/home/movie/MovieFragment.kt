@file:Suppress("KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation", "KotlinDeprecation",
    "KotlinDeprecation", "KotlinDeprecation", "RedundantNullableReturnType"
)

package com.adewijayanto.made.defilmsapp.home.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adewijayanto.made.core.data.Resources
import com.adewijayanto.made.core.ui.ListMovieAdapter
import com.adewijayanto.made.defilmsapp.databinding.FragmentMovieBinding
import com.adewijayanto.made.defilmsapp.detail.DetailActivity
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.DATA_MOVIE
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = ListMovieAdapter()
            movieAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DATA_MOVIE, it)
                intent.putExtra(NAME, "movie")
                startActivity(intent)
            }

            movieViewModel.movies.observe(viewLifecycleOwner) { listMovies ->
                if (listMovies != null) {
                    when (listMovies) {
                        is Resources.Loading -> showLoading(true)
                        is Resources.Success -> {
                            showLoading(false)
                            movieAdapter.setData(listMovies.data)
                        }
                        is Resources.Error -> {
                            showLoading(false)
                            Toast.makeText(
                                context,
                                "Kesalahan Ketika Memuat Data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            binding?.let {
                with(it.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.pbMovie?.visibility = View.VISIBLE
        } else {
            binding?.pbMovie?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}