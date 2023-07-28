package com.example.data.register

import com.example.data.register.datasources.RegisterLocalDataSource
import com.example.data.register.datasources.RegisterRemoteDataSource
import com.example.domain.register.dtos.User
import com.example.domain.register.repositories.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerRemoteDataSource: RegisterRemoteDataSource,
    private val registerLocalDataSource: RegisterLocalDataSource
        ): RegisterRepository {

    override suspend fun userIsRegistered(userToRegister: User) {
        registerRemoteDataSource.isRegisteredInFirebase(userToRegister)
        registerLocalDataSource.isRegistered(userToRegister)

    }

    override suspend fun registerUser(userToRegister: User) {
        registerRemoteDataSource.registerInFirabase(userToRegister)
        registerLocalDataSource.registerLocal(userToRegister)
    }
}