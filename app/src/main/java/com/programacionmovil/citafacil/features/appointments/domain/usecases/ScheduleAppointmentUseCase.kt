package com.programacionmovil.citafacil.features.appointments.domain.usecases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ScheduleAppointmentUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    suspend operator fun invoke(doctorName: String, date: String, reason: String): Result<Unit> = try {
        val uid = auth.currentUser?.uid ?: throw Exception("Usuario no autenticado")

        val appointment = hashMapOf(
            "patientId" to uid,
            "doctorName" to doctorName,
            "date" to date,
            "reason" to reason,
            "status" to "Pendiente"
        )

        firestore.collection("citas").add(appointment).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}