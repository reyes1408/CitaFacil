package com.programacionmovil.citafacil.features.auth.domain.repositories

import com.programacionmovil.citafacil.features.auth.domain.entities.User

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result<Unit>
    suspend fun register(email: String, pass: String, name: String): Result<Unit>
    suspend fun getUserData(uid: String): Result<User?>
    suspend fun saveUserData(user: User): Result<Unit>
    fun getCurrentUserUid(): String?

    fun logout()
}
