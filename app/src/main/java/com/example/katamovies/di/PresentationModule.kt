package com.example.katamovies.di

import com.example.domain.movies.repositories.MoviesRepository
import com.example.domain.register.repositories.SigUpRepository
import com.example.domain.sigin.repository.SigInRepository
import com.example.domain.movies.usecase.MoviesUseCase
import com.example.domain.sigin.usecases.SigIngUseCase
import com.example.domain.register.usescases.SigUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn (SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideUseCase(
        repository: SigInRepository
    ): SigIngUseCase {
        return SigIngUseCase(repository)
    }

    @Provides
    fun provideSigUpUseCase(
        repository: SigUpRepository
    ): SigUpUseCase {
        return SigUpUseCase(repository)
    }


    @Provides
    fun provideMoviesUseCase(
        moviesRepository: MoviesRepository
    ): MoviesUseCase {
        return MoviesUseCase( moviesRepository)
    }

}