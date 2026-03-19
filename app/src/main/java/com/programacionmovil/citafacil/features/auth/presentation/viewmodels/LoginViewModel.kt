package com.programacionmovil.citafacil.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programacionmovil.citafacil.features.auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)

    val state: StateFlow<AuthState> = _state

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            loginUseCase(email, pass).onSuccess {
                _state.value = AuthState.Success
            }.onFailure { exception ->
                _state.value = AuthState.Error(exception.message ?: "Error al iniciar sesión")
            }
        }
    }

    fun resetState() {
        _state.value = AuthState.Idle
    }
}