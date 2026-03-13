package com.programacionmovil.citafacil.features.auth.domain.usecases

import com.programacionmovil.citafacil.features.auth.domain.entities.User
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String, name: String): Result<User> {

        if (name.length < 3) {
            return Result.failure(Exception("El nombre es demasiado corto"))
        }
        return repository.register(email, pass, name)
    }
}