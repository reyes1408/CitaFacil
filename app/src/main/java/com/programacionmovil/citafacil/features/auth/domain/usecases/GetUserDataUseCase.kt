package com.programacionmovil.citafacil.features.auth.domain.usecases

import com.programacionmovil.citafacil.features.auth.domain.entities.User
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result<User?> {
        val uid = repository.getCurrentUserUid()
        return if (uid != null) {
            repository.getUserData(uid)
        } else {
            Result.failure(Exception("Usuario no autenticado"))
        }
    }
}