package com.programacionmovil.citafacil.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programacionmovil.citafacil.features.auth.domain.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun register(email: String, pass: String, name: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading

            val result = registerUseCase(email, pass, name)

            result.onSuccess { user ->
                _state.value = AuthState.Success
            }.onFailure { exception ->
                _state.value = AuthState.Error(exception.message ?: "Error al registrar")
            }
        }
    }
}