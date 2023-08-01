package com.example.domain.register.usescases

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import com.example.domain.register.repositories.SigUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SigIngUseCase(
    val repository: SigUpRepository
   ){

    suspend fun sigIngUser(user: UserDto): Flow<ResultMovies<UserDto, Exception>> {
        //repository.userIsRegistered(user)
        return repository.sigUpUser(user)
    }
}