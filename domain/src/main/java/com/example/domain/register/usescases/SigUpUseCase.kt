package com.example.domain.register.usescases

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.repositories.SigUpRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SigUpUseCase (
        private val sigUpRepository: SigUpRepository,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main)
{
        suspend fun sigUpUser(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>>  {
                 return sigUpRepository.sigUpUser(userToRegister)
        }

}