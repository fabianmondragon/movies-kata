package com.example.katamovies.di

import com.example.domain.register.repositories.MoviesRepository
import com.example.domain.register.repositories.SigUpRepository
import com.example.domain.register.sigin.repository.SigInRepository
import com.example.domain.register.usescases.MoviesUseCase
import com.example.domain.register.sigin.usecases.SigIngUseCase
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