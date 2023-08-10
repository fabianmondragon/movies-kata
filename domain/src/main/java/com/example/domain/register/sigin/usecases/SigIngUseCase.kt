package com.example.domain.register.sigin.usecases

import com.example.domain.register.ResultMovies
import com.example.domain.register.sigin.repository.SigInRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SigIngUseCase(
    private val sigInRepository: SigInRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
   ){
    suspend fun sigIngUser(email: String, password: String): Flow<ResultMovies<Boolean, Exception>> = flow<ResultMovies<Boolean, Exception>> {
        //repository.userIsRegistered(user)
         sigInRepository.sigIn(email = email, password = password)
    }.flowOn(dispatcher)
}