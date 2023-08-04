package com.example.data.movies.datasources

import com.example.data.database.MovieDao
import com.example.data.database.entities.MovieEntity
import com.example.data.movies.mappers.MapperMovies
import com.example.data.movies.mappers.toMovieD
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.MovieD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
class MoviesLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
): MoviesLocalDataSource {
    override suspend fun getMovies(): Flow<ResultMovies<List<MovieD>, Exception>> {
        val listToConvertToDomain = movieDao.getAllMovies()
        val listToReturn = listToConvertToDomain.map {
                movieEntity ->
            movieEntity.toMovieD()
        }
        return if (listToReturn.isNotEmpty())
            flow{
                emit(ResultMovies.Success(listToReturn))
            }
        else
            flow {
                val error = IOException("Error dataBase")
                emit (ResultMovies.Error(error))
            }
    }
    override suspend fun saveMovies(movieEntity: MovieEntity) {
        movieDao.insertAll(movieEntity)
    }
}