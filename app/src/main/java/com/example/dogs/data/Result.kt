package com.example.dogs.data

sealed class Result<out T : Any> {
    data object Loading : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String?) : Result<Nothing>()
}