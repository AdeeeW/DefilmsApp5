@file:Suppress("RemoveExplicitTypeArguments")

package com.adewijayanto.made.core.data

import com.adewijayanto.made.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkResourceBound<ResultType, RequestType> {
    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDB(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)

    private var result: Flow<Resources<ResultType>> = flow {
        emit(Resources.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resources.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resources.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resources.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resources.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resources.Success(it) })
        }
    }

    fun asFlow(): Flow<Resources<out ResultType?>> = result
}