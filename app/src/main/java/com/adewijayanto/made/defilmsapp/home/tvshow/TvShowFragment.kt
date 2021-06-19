package com.adewijayanto.made.defilmsapp.home.tvshow

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
import com.adewijayanto.made.core.ui.ListTvShowAdapter
import com.adewijayanto.made.defilmsapp.databinding.FragmentTvShowBinding
import com.adewijayanto.made.defilmsapp.detail.DetailActivity
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.DATA_TVSHOW
import com.adewijayanto.made.defilmsapp.detail.DetailActivity.Companion.NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private var binding: FragmentTvShowBinding? = null

    private val tvshowViewModel: TvShowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val tvShowAdapter = ListTvShowAdapter()
            tvShowAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DATA_TVSHOW, it)
                intent.putExtra(NAME, "tv_show")
                startActivity(intent)
            }

            tvshowViewModel.tvshow.observe(viewLifecycleOwner) { listTvShow ->
                if (listTvShow != null) {
                    when (listTvShow) {
                        is Resources.Loading -> showLoading(true)
                        is Resources.Success -> {
                            showLoading(false)
                            tvShowAdapter.setData(listTvShow.data)
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
                with(it.rvTvshow) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.pbTvshow?.visibility = View.VISIBLE
        } else {
            binding?.pbTvshow?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}