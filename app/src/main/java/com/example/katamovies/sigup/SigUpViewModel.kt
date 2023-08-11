package com.example.katamovies.sigup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.register.ResultMovies
import com.example.domain.register.dtos.UserD
import com.example.domain.register.usescases.SigUpUseCase
import com.example.katamovies.di.IoDispatcher
import com.example.katamovies.sigin.models.ResultSigUpUi
import com.example.katamovies.utils.convertParamsToUserD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SigUpViewModel @Inject constructor(
    private val sigUpUseCase: SigUpUseCase,
    @IoDispatcher private val testDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _resultListSigUpUi: MutableStateFlow<ResultSigUpUi> = MutableStateFlow(
        ResultSigUpUi()
    )
    val resultListSigUpUi = _resultListSigUpUi.asStateFlow()

    fun sigUpUser(userD: UserD) {

        viewModelScope.launch(testDispatcher) {
            _resultListSigUpUi.value =
                ResultSigUpUi(showIsLoading = true, answered = false, isSuccess = false)
            sigUpUseCase.sigUpUser(userD)
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

    fun transform(userName: String, email: String, password: String): UserD {
        return convertParamsToUserD(userName, email, password)
    }

}