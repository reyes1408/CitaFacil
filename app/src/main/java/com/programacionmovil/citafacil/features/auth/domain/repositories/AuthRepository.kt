package com.programacionmovil.citafacil.features.auth.domain.repositories

import com.programacionmovil.citafacil.features.auth.domain.entities.User

interface AuthRepository {
    suspend fun login(email: String, pass: String): Result <User>
    suspend fun register(email:String, pass: String, name: String): Result<User>
    fun getCurrentUser(): User?
    fun logout()
}
