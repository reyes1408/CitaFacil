package com.programacionmovil.citafacil.core.di

import com.google.firebase.auth.FirebaseAuth
import com.programacionmovil.citafacil.features.auth.data.repositories.AuthRepositoryImpl
import com.programacionmovil.citafacil.features.auth.domain.repositories.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}
