package com.example.data.register

import com.example.data.register.datasources.SigUpLocalDataSource
import com.example.data.register.datasources.SigUpRemoteDataSource
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import com.example.domain.register.repositories.SigUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SigUpRepositoryImpl @Inject constructor(
    private val sigUpRemoteDataSource: SigUpRemoteDataSource,
    private val sigUpLocalDataSource: SigUpLocalDataSource
) : SigUpRepository {
    override suspend fun userIsSigUp(userToRegister: UserDto) {

    }

    override suspend fun sigUpUser(userToRegister: UserDto): Flow<ResultMovies<UserDto, Exception>> {
        try {
            val result = sigUpRemoteDataSource
                .sigUpInFirebase(userToRegister)
            return result.map { response ->
                when (response) {
                    is ResultMovies.Success -> {

                        ResultMovies.Success(value = response.value)
                        //registerLocalDataSource.saveUser(user = response.value)
                        // todo save in local data base
                    }
                    else -> {
                        val error: Exception = java.lang.Exception("There is a problem")
                        ResultMovies.Error(error = error)
                    }
                }
            }
        }catch (e: Exception){
            println("${e.message}")
        }
        return flow {
            val error: Exception = java.lang.Exception("There is a problem")
            ResultMovies.Error(error = error)
        }
    }
}