package com.example.data.movies.datasources

import com.example.data.database.entities.MovieEntity

interface MoviesLocalDataSource {

    suspend fun getMovies()
    suspend fun saveMovies(movies: MovieEntity )
}