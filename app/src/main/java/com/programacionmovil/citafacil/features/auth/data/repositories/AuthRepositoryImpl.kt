package com.programacionmovil.citafacil.features.auth.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.programacionmovil.citafacil.features.auth.domain.entities.User
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, pass: String): Result<User> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            val firebaseUser = result.user
            if (firebaseUser != null) {
                Result.success(User(id = firebaseUser.uid, email = firebaseUser.email ?: ""))
            } else {
                Result.failure(Exception("Usuario no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(email: String, pass: String, name: String): Result<User> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
            val firebaseUser = result.user
            if (firebaseUser != null) {
                Result.success(User(id = firebaseUser.uid, email = firebaseUser.email ?: "", displayName = name))
            } else {
                Result.failure(Exception("Error al registrar"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserData(uid: String): Result<User?> = try {
        val document = firestore.collection("usuarios").document(uid).get().await()
        val user = document.toObject(User::class.java)
        Result.success(user)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getCurrentUser(): User? {
        return firebaseAuth.currentUser?.let {
            User(id = it.uid, email = it.email ?: "")
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}