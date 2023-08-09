package com.example.domain.register.sigin.usecases

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.repositories.SigUpRepository
import com.example.domain.register.sigin.repository.SigInRepository
import kotlinx.coroutines.flow.Flow

class SigIngUseCase(
    private val sigInRepository: SigInRepository
   ){
    suspend fun sigIngUser(email: String, password: String): Flow<ResultMovies<Boolean, Exception>> {
        //repository.userIsRegistered(user)
        return sigInRepository.sigIn(email = email, password = password)
    }
}