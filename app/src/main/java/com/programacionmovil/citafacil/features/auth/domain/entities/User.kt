package com.programacionmovil.citafacil.features.auth.domain.entities

data class User (
    val id: String,
    val email: String,
    val displayName: String? = null
)