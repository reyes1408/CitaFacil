package com.programacionmovil.citafacil.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.programacionmovil.citafacil.features.auth.presentation.screens.login.LoginScreen
import com.programacionmovil.citafacil.features.auth.presentation.screens.register.RegisterScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }

        composable("register") {
            RegisterScreen(
                onNavigateBack = { navController.popBackStack() }, // Regresa al Login
                onRegisterSuccess = {
                    // Al registrarse con éxito pasa a Home
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // Limpia el historial
                    }
                }
            )
        }

        composable("home") {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("¡Bienvenido a la Home!")
            }
        }
    }
}