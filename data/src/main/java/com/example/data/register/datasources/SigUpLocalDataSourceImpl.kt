package com.example.data.register.datasources

import com.example.domain.register.dtos.UserDto
import javax.inject.Inject

class SigUpLocalDataSourceImpl @Inject constructor(): SigUpLocalDataSource {

    override suspend fun saveUser(user: UserDto) {
        TODO("Not yet implemented")
    }

    override suspend fun isRegistered(userToRegister: UserDto) {
        TODO("Not yet implemented")
    }

    override suspend fun registerLocal(userToRegister: UserDto) {
        TODO("Not yet implemented")
    }
}