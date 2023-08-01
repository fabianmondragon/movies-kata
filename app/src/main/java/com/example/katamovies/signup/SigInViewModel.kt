package com.example.katamovies.signup

import androidx.lifecycle.ViewModel
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserDto
import com.example.domain.register.usescases.SigIngUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val sigIngUseCase: SigIngUseCase
): ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    fun logIn(){
        coroutineScope.launch {
            sigIngUseCase.sigIngUser(
                UserDto(
                    userName = "mondra13@gmail.com",
                    password = "12345678",
                    email = "mondra16@gmail.com"
                )
            ).collect { response ->
                when (response){
                    is ResultMovies.Success -> {
                        val result = response.value
                    }
                    else -> {

                    }
                }

            }

        }
    }
}