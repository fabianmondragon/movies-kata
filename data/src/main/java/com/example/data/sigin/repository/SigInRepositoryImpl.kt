package com.example.data.sigin.repository

import com.example.data.sigin.repository.datasources.definition.SigInRemoteDataSource
import com.example.domain.register.ResultMovies
import com.example.domain.sigin.repository.SigInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SigInRepositoryImpl @Inject constructor(
    private val sigInRemoteDataSource: SigInRemoteDataSource
): SigInRepository {
    override suspend fun sigIn(
        email: String,
        password: String
    ): Flow<ResultMovies<Boolean, Exception>> {
        return sigInRemoteDataSource.sigIn(email, password)
    }

}