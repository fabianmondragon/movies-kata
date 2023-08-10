package com.example.katamovies.sigin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.register.ResultMovies
import com.example.domain.register.sigin.usecases.SigIngUseCase
import com.example.katamovies.sigin.models.SigInUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val sigIngUseCase: SigIngUseCase
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _sigInUi: MutableStateFlow<SigInUi> = MutableStateFlow(
        SigInUi()
    )
    val sigInUi = _sigInUi.asStateFlow()

    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val emailErrorState = mutableStateOf(false)
    val passwordErrorState = mutableStateOf(false)

    fun sigIn() {
        val email = emailState.value
        val password = passwordState.value

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailErrorState.value = true
            return
        }
        if (password.isBlank()) {
            passwordErrorState.value = true
            return
        }
        coroutineScope.launch {
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
                            showIsLoading = false, messageToShow = response
                                .error
                                .message.toString(), showMessageFromSigUp = false
                        )
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String) {
        if (email.isBlank())
            _sigInUi.value = SigInUi(messageToShow = "Please, there is a")

    }

    fun validateShowMessage(messageFromSigUp: String?) {
        if (messageFromSigUp != null) {
            _sigInUi.value = _sigInUi.value.copy(showMessageFromSigUp = true)
        }
    }
}