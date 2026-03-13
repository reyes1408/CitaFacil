package com.programacionmovil.citafacil.features.auth.presentation.viewmodels

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: com.programacionmovil.citafacil.features.auth.domain.entities.User) : AuthState()
    data class Error (val message: String) : AuthState()
}