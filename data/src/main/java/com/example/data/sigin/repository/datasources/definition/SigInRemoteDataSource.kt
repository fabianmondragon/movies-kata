package com.example.data.sigin.repository.datasources.definition

import com.example.domain.register.ResultMovies
import kotlinx.coroutines.flow.Flow


interface SigInRemoteDataSource {

    suspend fun sigIn(email:String, password: String): Flow<ResultMovies<Boolean, Exception>>

}