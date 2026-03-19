package com.programacionmovil.citafacil.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programacionmovil.citafacil.core.common.HardwareManager
import com.programacionmovil.citafacil.features.appointments.presentation.screens.ScheduleScreen
import com.programacionmovil.citafacil.features.auth.presentation.screens.login.LoginScreen
import com.programacionmovil.citafacil.features.auth.presentation.screens.register.RegisterScreen
import com.programacionmovil.citafacil.features.profile.presentation.screens.ProfileScreen
import com.programacionmovil.citafacil.features.home.presentation.screens.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    hardwareManager: HardwareManager,
    startDestination: String = "login"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // Pantalla de Inicio de Sesión
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }

        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Pantalla Principal (Menú)
        composable("home") {
            HomeScreen(
                onNavigateToSchedule = { navController.navigate("schedule") },
                onNavigateToProfile = { navController.navigate("profile") }
            )
        }

        // Pantalla de Agendar Cita (Cámara para Evidencia)
        composable("schedule") {
            ScheduleScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        // Pantalla de Perfil (Cámara para Foto)
        composable("profile") {
            ProfileScreen(
                hardwareManager = hardwareManager
            )
        }
    }
}