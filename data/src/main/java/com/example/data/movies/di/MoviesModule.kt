package com.example.data.movies.di

import com.example.data.movies.MoviesRepositoryImpl
import com.example.data.movies.datasources.MoviesLocalDataSource
import com.example.data.movies.datasources.MoviesLocalDataSourceImpl
import com.example.data.movies.datasources.MoviesRemoteDataSource
import com.example.data.movies.datasources.MoviesRemoteDataSourceImpl
import com.example.domain.register.repositories.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MoviesModule {

    @Binds
    fun bindMoviesRepository(
     moviesRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    fun bindMoviesRemoteDataSource(
        moviesRemoteDataSource: MoviesRemoteDataSourceImpl
    ): MoviesRemoteDataSource

    @Binds
    fun bindMoviesLocalDataSource(
        moviesLocalDataSource: MoviesLocalDataSourceImpl
    ): MoviesLocalDataSource
}