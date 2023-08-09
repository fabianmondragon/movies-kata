package com.example.data.register.di

import com.example.data.register.SigUpRepositoryImpl
import com.example.data.register.datasources.FirebaseSigUpRemoteDataSourceImpl
import com.example.data.register.datasources.SigUpLocalDataSource
import com.example.data.register.datasources.SigUpLocalDataSourceImpl
import com.example.data.register.datasources.SigUpRemoteDataSource
import com.example.data.sigin.repository.SigInRepositoryImpl
import com.example.data.sigin.repository.datasources.definition.SigInRemoteDataSource
import com.example.data.sigin.repository.datasources.impl.FirebaseSigInRemoteDataSourceImpl
import com.example.domain.register.repositories.SigUpRepository
import com.example.domain.register.sigin.repository.SigInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSigUpRepository(
        sigUpRepositoryImpl: SigUpRepositoryImpl
    ): SigUpRepository

    @Binds
    fun bindSigInRepository(
        sigInRepositoryImpl: SigInRepositoryImpl
    ): SigInRepository

    @Binds
    fun bindSigUpRemoteDataSource(
        sigUpRemoteDataSourceImpl: FirebaseSigUpRemoteDataSourceImpl
    ): SigUpRemoteDataSource

    @Binds
    fun bindSigInRemoteDataSource(
        sigInRemoteDataSourceImpl: FirebaseSigInRemoteDataSourceImpl
    ): SigInRemoteDataSource

    @Binds
    fun bindSigUpLocalDataSource(
        sigUpLocalDataSourceImpl: SigUpLocalDataSourceImpl
    ): SigUpLocalDataSource

}