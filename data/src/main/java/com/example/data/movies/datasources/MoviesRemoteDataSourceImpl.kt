package com.example.data.movies.datasources

import com.example.data.movies.mappers.MapperMovies
import com.example.data.movies.response.Movie
import com.example.data.movies.service.MoviesService
import com.example.data.movies.response.MovieResponse
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
) : MoviesRemoteDataSource {
    override suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>> {
        val response = moviesService.getTopRatedMovies()
        return if (response.isSuccessful) {
            flow {
                val listToDomain= response.body()
                    ?.let { MapperMovies.convertToListMovieDataToListMovieDomain(it.results) }
                emit(ResultMovies.Success(listToDomain!!))
            }
        } else {
            flow {
                val error = IOException("Error ${response.code()}: ${response.message()}")
                emit (ResultMovies.Error(error))
            }
        }
    }
}