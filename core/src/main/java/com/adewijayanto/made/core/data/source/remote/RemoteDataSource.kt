package com.adewijayanto.made.core.data.source.remote

import android.util.Log
import com.adewijayanto.made.core.data.source.remote.network.ApiResponse
import com.adewijayanto.made.core.data.source.remote.network.ApiService
import com.adewijayanto.made.core.data.source.remote.response.ResultsMovieItem
import com.adewijayanto.made.core.data.source.remote.response.ResultsTvShowItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    companion object {
        var TAG = "RemoteDataSource"
    }

    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsMovieItem>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val movie = response.results
                if (movie.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<ResultsTvShowItem>>> {
        return flow {
            try {
                val response = apiService.getTvShows()
                val tvshow = response.results
                if (tvshow.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}