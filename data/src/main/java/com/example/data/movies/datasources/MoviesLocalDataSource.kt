package com.example.data.movies.datasources

interface MoviesLocalDataSource {

    suspend fun getMovies()
    suspend fun saveMovies()
}