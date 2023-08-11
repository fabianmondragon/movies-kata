package com.example.domain.sigin.repository

import com.example.domain.register.ResultMovies
import kotlinx.coroutines.flow.Flow

interface SigInRepository {

    suspend fun sigIn(email: String, password: String): Flow<ResultMovies<Boolean, Exception>>
}