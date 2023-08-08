package com.example.data.register.datasources

import com.example.domain.register.dtos.UserD
import javax.inject.Inject

class SigUpLocalDataSourceImpl @Inject constructor(): SigUpLocalDataSource {

    override suspend fun saveUser(user: UserD) {
        TODO("Not yet implemented")
    }

    override suspend fun isRegistered(userToRegister: UserD) {
        TODO("Not yet implemented")
    }

    override suspend fun registerLocal(userToRegister: UserD) {
        TODO("Not yet implemented")
    }
}