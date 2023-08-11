package com.example.katamovies.sigin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.register.ResultMovies
import com.example.domain.sigin.usecases.SigIngUseCase
import com.example.katamovies.di.IoDispatcher
import com.example.katamovies.sigin.models.SigInUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val sigIngUseCase: SigIngUseCase,
    @IoDispatcher private val testDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _sigInUi: MutableStateFlow<SigInUi> = MutableStateFlow(
        SigInUi()
    )
    val sigInUi = _sigInUi.asStateFlow()

    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val emailErrorState = mutableStateOf(false)
    val passwordErrorState = mutableStateOf(false)

    fun sigIn(email: String, password: String) {

        viewModelScope.launch(testDispatcher) {
            _sigInUi.value = SigInUi(showIsLoading = true, showMessageFromSigUp = false)
            sigIngUseCase.sigIngUser(
                email = email,
                password = password
            ).collect { response ->
                when (response) {
                    is ResultMovies.Success -> {
                        delay(2000)
                        _sigInUi.value =
                            SigInUi(
                                showIsLoading = false,
                                showMessageFromSigUp = false,
                                goToMovies = true
                            )
                    }
                    is ResultMovies.Error -> {
                        delay(2000)
                        _sigInUi.value = SigInUi(
                            showIsLoading = false,
                            messageToShow = response
                                .error
                                .message.toString(), showMessageFromSigUp = false
                        )
                    }
                }
            }
        }
    }

    fun isEmailValid(): Boolean {
        val email = emailState.value
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        if (!email.matches(emailRegex.toRegex())) {
            emailErrorState.value = true
            return false
        }
        return true
    }

    fun isPasswordValid(): Boolean {
        /*
        val password = passwordState.value
        val passwordRegex =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
        if (!password.matches(passwordRegex)) {
            passwordErrorState.value = true
            return false
        }*/
        return true
    }

}