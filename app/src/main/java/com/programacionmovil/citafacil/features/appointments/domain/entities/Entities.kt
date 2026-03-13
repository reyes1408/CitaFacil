package com.programacionmovil.citafacil.features.appointments.domain.entities

data class Appointment(
    val id: String = "",
    val patientId: String = "",
    val doctorName: String = "",
    val date: String = "",
    val reason: String = "",
    val status: String = "Pendiente"
)