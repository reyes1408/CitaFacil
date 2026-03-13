package com.programacionmovil.citafacil.features.auth.domain.usecases

import com.programacionmovil.citafacil.features.auth.domain.entities.User
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String): Result<User> {
        if (email.isBlank() || pass.isBlank()) {
            return Result.failure(Exception("El correo y la contraseña no pueden estar vacíos"))
        }
        return repository.login(email, pass)
    }
}