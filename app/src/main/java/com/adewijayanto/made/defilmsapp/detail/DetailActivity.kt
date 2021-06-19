package com.adewijayanto.made.defilmsapp.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import com.adewijayanto.made.core.BuildConfig
import com.adewijayanto.made.core.domain.model.Movie
import com.adewijayanto.made.core.domain.model.TvShow
import com.adewijayanto.made.defilmsapp.R
import com.adewijayanto.made.defilmsapp.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(DATA_MOVIE)
        val detailTvShow = intent.getParcelableExtra<TvShow>(DATA_TVSHOW)
        val name = intent.extras?.getString(NAME)

        if (name == "movie") {
            if (detailMovie != null) {
                getMovie(detailMovie)
                showLoading(false)
                supportActionBar?.title = getString(R.string.detail_movie)
            }
        } else {
            if (detailTvShow != null) {
                getTvShow(detailTvShow)
                showLoading(false)
                supportActionBar?.title = getString(R.string.detail_tvshow)
                binding.titleTv.visibility = View.VISIBLE
                binding.dateFirst.visibility = View.VISIBLE
                binding.titleMovie.visibility = View.GONE
                binding.dateReleas.visibility = View.GONE
            }
        }
    }

    private fun getTvShow(detailTvShow: TvShow) {
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + detailTvShow.poster_path)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivPoster)
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + detailTvShow.poster_path)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .thumbnail()
            .centerCrop()
            .into(binding.imgBg)
        binding.tvDetailJudul.text = detailTvShow.name
        binding.tvDetailDate.text = detailTvShow.first_air_date
        binding.barRating.rating = detailTvShow.vote_average
        binding.tvDetailDeskripsi.text = detailTvShow.overview

        var isFavorite = detailTvShow.favorite
        setFavorite(isFavorite)
        binding.btnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteTvShow(detailTvShow, isFavorite)
            setFavorite(isFavorite)

            if (isFavorite) {
                Toast.makeText(this, getString(R.string.add_fav_tv), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.remove_fav_tv), Toast.LENGTH_SHORT).show()
            }
        }

        binding.fbShare.setOnClickListener {
            shareApp(detailTvShow)
        }
    }

    private fun getMovie(detailMovie: Movie) {
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + detailMovie.poster_path)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivPoster)
        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + detailMovie.poster_path)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .thumbnail()
            .centerCrop()
            .into(binding.imgBg)
        binding.tvDetailJudul.text = detailMovie.title
        binding.tvDetailDate.text = detailMovie.release_date
        binding.barRating.rating = detailMovie.vote_average
        binding.tvDetailDeskripsi.text = detailMovie.overview

        var isFavorite = detailMovie.favorite
        setFavorite(isFavorite)
        binding.btnFavorite.setOnClickListener {
            isFavorite = !isFavorite
            detailViewModel.setFavoriteMovies(detailMovie, isFavorite)
            setFavorite(isFavorite)

            if (isFavorite) {
                Toast.makeText(this, getString(R.string.add_fav), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.remove_fav), Toast.LENGTH_SHORT).show()
            }
        }

        binding.fbShare.setOnClickListener {
            shareApp(detailMovie)
        }
    }

    private fun shareApp(tvshow: TvShow) {
        val mimeType = "text/plain"
        tvshow.apply {
            ShareCompat.IntentBuilder
                .from(this@DetailActivity)
                .setType(mimeType)
                .setChooserTitle("Bagikan TvShow: \"${name}\", sekarang.")
                .setText(
                    "Judul TvShow: ${name}\n" +
                            "Deskripsi TvShow: ${overview}\n"
                )
                .startChooser()
        }
    }

    private fun shareApp(movies: Movie) {
        val mimeType = "text/plain"
        movies.apply {
            ShareCompat.IntentBuilder
                .from(this@DetailActivity)
                .setType(mimeType)
                .setChooserTitle("Bagikan Film: \"${title}\", sekarang.")
                .setText(
                    "Judul Film: ${title}\n" +
                            "Deskripsi Film: ${overview}\n"
                )
                .startChooser()
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_red
                )
            )
        } else {
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }

    companion object {
        const val DATA_MOVIE = "movie"
        const val DATA_TVSHOW = "tv_show"
        const val NAME = "name"
    }
}