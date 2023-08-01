package com.example.data.register

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import com.example.domain.register.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
        ): RegisterRepository {

    override suspend fun userIsRegistered(userToRegister: UserDto) {
    }

    override suspend fun registerUser(userToRegister: UserDto):
            Flow<ResultMovies<UserDto, Exception>> {
        return flow {
            ResultMovies.Success(value = UserDto())
        }
    }
}