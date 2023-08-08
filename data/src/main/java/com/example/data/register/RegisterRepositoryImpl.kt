package com.example.data.register

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
        ): RegisterRepository {

    override suspend fun userIsRegistered(userToRegister: UserD) {
    }

    override suspend fun registerUser(userToRegister: UserD):
            Flow<ResultMovies<UserD, Exception>> {
        return flow {
            ResultMovies.Success(value = UserD())
        }
    }
}