package com.example.data.movies.datasources

import com.example.data.database.MovieDao
import com.example.data.database.entities.MovieEntity
import javax.inject.Inject
class MoviesLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
): MoviesLocalDataSource {
    override suspend fun getMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovies(movieEntity: MovieEntity) {
        movieDao.insertAll(movieEntity)
    }
}