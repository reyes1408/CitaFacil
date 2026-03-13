package com.programacionmovil.citafacil.features.auth.data.models

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "patient"
)