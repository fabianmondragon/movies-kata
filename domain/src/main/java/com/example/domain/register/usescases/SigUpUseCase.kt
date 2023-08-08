package com.example.domain.register.usescases

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.repositories.SigUpRepository
import kotlinx.coroutines.flow.Flow

class SigUpUseCase (private val sigUpRepository: SigUpRepository)
{
        suspend fun sigUpUser(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>> {
                return sigUpRepository.sigUpUser(userToRegister)
        }

}