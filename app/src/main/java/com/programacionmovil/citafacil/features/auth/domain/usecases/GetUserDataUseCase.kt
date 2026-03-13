package com.programacionmovil.citafacil.features.auth.domain.usecases

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