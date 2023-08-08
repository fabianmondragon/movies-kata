package com.example.domain.register.usescases

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.repositories.SigUpRepository
import kotlinx.coroutines.flow.Flow

class SigIngUseCase(
    private val sigUpRepository: SigUpRepository
   ){
    suspend fun sigIngUser(user: UserD): Flow<ResultMovies<UserD, Exception>> {
        //repository.userIsRegistered(user)
        return sigUpRepository.sigUpUser(user)
    }
}