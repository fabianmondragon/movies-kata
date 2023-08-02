package com.example.domain.register.usescases

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import com.example.domain.register.repositories.MoviesRepository
import com.example.domain.register.repositories.SigUpRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SigIngUseCase(
    private val sigUpRepository: SigUpRepository
   ){
    suspend fun sigIngUser(user: UserDto): Flow<ResultMovies<UserDto, Exception>> {
        //repository.userIsRegistered(user)
        return sigUpRepository.sigUpUser(user)
    }
}