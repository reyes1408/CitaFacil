package com.programacionmovil.citafacil.features.auth.domain.entities

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "patient"
)