package com.example.data.register.datasources

import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SigUpRemoteDataSourceImpl @Inject constructor(): SigUpRemoteDataSource {


    override suspend fun isSigUpInFirebase(userToRegister: UserD) {
        TODO("Not yet implemented")
    }

    override suspend fun sigUpInFirebase(userToRegister: UserD): Flow<ResultMovies<UserD, Exception>> {
        TODO("Not yet implemented")
    }

}