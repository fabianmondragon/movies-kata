package com.example.katamovies.sigup

import androidx.lifecycle.ViewModel
import com.example.domain.register.ResultMovies
import com.example.domain.register.usescases.SigUpUseCase
import com.example.katamovies.sigin.models.ResultSigUpUi
import com.example.katamovies.utils.convertParamsToUserD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SigUpViewModel @Inject constructor(
    private val sigUpUseCase: SigUpUseCase
) : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _resultListSigUpUi: MutableStateFlow<ResultSigUpUi> = MutableStateFlow(
        ResultSigUpUi()
    )
    val resultListSigUpUi = _resultListSigUpUi.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun sigUpUser(userName: String, email: String, password: String) {
        val userToSigUp = convertParamsToUserD(userName, email, password)
        coroutineScope.launch {
            _resultListSigUpUi.value =
                ResultSigUpUi(showIsLoading = true, answered = false, isSuccess = false)
            sigUpUseCase.sigUpUser(userToSigUp)
                .collect { result ->
                    when (result) {
                        is ResultMovies.Success -> {
                            _resultListSigUpUi.value = ResultSigUpUi(
                                showIsLoading = false,
                                answered = true,
                                isSuccess = true,
                                messageToShow = "Usuario registrado con éxito"
                            )
                        }
                        else -> {
                            delay(2000)
                            _resultListSigUpUi.value = ResultSigUpUi(
                                showIsLoading = false,
                                answered = true,
                                isSuccess = false,
                                messageToShow = "Occurrio un error"
                            )

                        }
                    }
                }
        }
    }

}