package com.example.data.movies.datasources

import com.example.data.movies.response.Movie
import com.example.data.movies.response.MovieResponse
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {

    suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>>
}