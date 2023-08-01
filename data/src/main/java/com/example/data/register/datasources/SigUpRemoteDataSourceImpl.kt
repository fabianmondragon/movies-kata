package com.example.data.register.datasources

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SigUpRemoteDataSourceImpl @Inject constructor(): SigUpRemoteDataSource {


    override suspend fun isSigUpInFirebase(userToRegister: UserDto) {
        TODO("Not yet implemented")
    }

    override suspend fun sigUpInFirebase(userToRegister: UserDto): Flow<ResultMovies<UserDto, Exception>> {
        TODO("Not yet implemented")
    }

}