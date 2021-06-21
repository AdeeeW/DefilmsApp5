package com.adewijayanto.made.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adewijayanto.made.core.BuildConfig.IMAGE_URL
import com.adewijayanto.made.core.R
import com.adewijayanto.made.core.databinding.ListItemBinding
import com.adewijayanto.made.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@Suppress("DEPRECATION")
class ListMovieAdapter: RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder>() {
    private var listDataMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    companion object{
        const val TAG = "ADAPTER"
    }

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listDataMovie.clear()
        listDataMovie.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listDataMovie[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listDataMovie.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(movie: Movie){
            val poster = StringBuilder("$IMAGE_URL${movie.poster_path}").toString()
            with(binding) {
                Glide.with(itemView.context)
                    .load(poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .centerCrop()
                    .into(imgPoster)
                Log.d(TAG, movie.release_date)

                tvTitle.text = movie.title
                tvDeskripsi.text = movie.overview
                tvDate.text = movie.release_date
                barRating.rating = movie.vote_average
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listDataMovie[adapterPosition])
            }
        }
    }
}