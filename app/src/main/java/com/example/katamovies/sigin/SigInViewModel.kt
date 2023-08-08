package com.example.katamovies.sigin

import androidx.lifecycle.ViewModel
import com.example.domain.register.usescases.SigIngUseCase
import com.example.katamovies.sigin.models.SigInUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SigInViewModel @Inject constructor(
    private val sigIngUseCase: SigIngUseCase
): ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _sigInUi : MutableStateFlow<SigInUi> = MutableStateFlow(
        SigInUi()
    )
    val sigInUi = _sigInUi.asStateFlow()

    fun logIn(messageFromSigUp: String?) {
        /*
        coroutineScope.launch {
            sigIngUseCase.sigIngUser(
                UserD(
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

         */
    }

    fun validateShowMessage(messageFromSigUp: String?) {
        if (messageFromSigUp!= null){
            _sigInUi.value = _sigInUi.value.copy(showMessage = true)
        }
    }
}