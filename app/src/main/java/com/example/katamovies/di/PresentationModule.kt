package com.example.katamovies.di

import com.example.domain.register.repositories.SigUpRepository
import com.example.domain.register.usescases.SigIngUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn (SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideUseCase(
        repository: SigUpRepository
    ): SigIngUseCase {
        return SigIngUseCase(repository)
    }
}