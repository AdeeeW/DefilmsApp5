@file:Suppress("KDocMissingDocumentation", "KDocMissingDocumentation", "KDocMissingDocumentation",
    "KDocMissingDocumentation", "KDocMissingDocumentation", "KDocMissingDocumentation", "Reformat",
    "Reformat"
)

package com.adewijayanto.made.core.data.source.remote.network


sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}