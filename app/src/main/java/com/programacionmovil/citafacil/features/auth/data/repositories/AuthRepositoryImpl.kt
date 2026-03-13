package com.programacionmovil.citafacil.features.auth.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.programacionmovil.citafacil.features.auth.domain.entities.User
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun login(email: String, pass: String): Result<Unit> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(email: String, pass: String, name: String): Result<Unit> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
            Result.success(Unit)
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

    override suspend fun saveUserData(user: User): Result<Unit> = try {
        firestore.collection("usuarios").document(user.uid).set(user).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getCurrentUserUid(): String? {
        return firebaseAuth.currentUser?.uid
    }
}