package com.programacionmovil.citafacil.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programacionmovil.citafacil.features.auth.domain.usecases.GetUserDataUseCase
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository // Importante
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val repository: AuthRepository
) : ViewModel() {

    var userName by mutableStateOf("Cargando...")
        private set

    var isLoggedOut by mutableStateOf(false)
        private set

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            getUserDataUseCase().onSuccess { user ->
                userName = user?.name ?: "Sin nombre"
            }.onFailure {
                userName = "Error de conexión"
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            isLoggedOut = true
        }
    }
}