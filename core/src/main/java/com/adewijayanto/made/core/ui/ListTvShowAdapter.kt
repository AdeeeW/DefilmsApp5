package com.adewijayanto.made.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adewijayanto.made.core.BuildConfig.IMAGE_URL
import com.adewijayanto.made.core.R
import com.adewijayanto.made.core.databinding.ListItemBinding
import com.adewijayanto.made.core.domain.model.TvShow
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@Suppress("DEPRECATION")
class ListTvShowAdapter : RecyclerView.Adapter<ListTvShowAdapter.TvShowViewHolder>() {
    private var listDataTvShow = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    companion object {
        const val TAG = "ADAPTER"
    }

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        listDataTvShow.clear()
        listDataTvShow.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder =
        TvShowViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val data = listDataTvShow[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listDataTvShow.size

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind(shows: TvShow) {
            val poster = StringBuilder("${IMAGE_URL}${shows.poster_path}").toString()
            with(binding) {
                Glide.with(itemView.context)
                    .load(poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .centerCrop()
                    .into(imgPoster)
                Log.d(TAG, shows.first_air_date)

                tvTitle.text = shows.name
                tvDeskripsi.text = shows.overview
                tvDate.text = shows.first_air_date
                barRating.rating = shows.vote_average
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listDataTvShow[adapterPosition])
            }
        }
    }
}