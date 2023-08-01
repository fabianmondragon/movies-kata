package com.example.domain.register

sealed class ResultMovies <out T, out E> {
    data class Success<T>(val value: T) : ResultMovies<T, Nothing>()
    data class Error<E>(val error: E) : ResultMovies<Nothing, E>()
}
