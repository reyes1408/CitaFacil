package com.programacionmovil.citafacil.features.auth.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.programacionmovil.citafacil.features.auth.presentation.viewmodels.LoginViewModel
import com.programacionmovil.citafacil.features.auth.presentation.viewmodels.AuthState
import com.programacionmovil.citafacil.features.auth.presentation.components.AuthTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Cita Fácil", style = MaterialTheme.typography.headlineLarge)

        AuthTextField(value = email, onValueChange = { email = it }, label = "Correo")
        AuthTextField(value = password, onValueChange = { password = it }, label = "Contraseña", isPassword = true)

        Button(
            onClick = { viewModel.login(email, password) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            enabled = state !is AuthState.Loading
        ) {
            Text("Iniciar Sesión")
        }

        TextButton(onClick = onNavigateToRegister) {
            Text("¿No tienes cuenta? Regístrate")
        }

        when (state) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success -> LaunchedEffect(Unit) { onLoginSuccess() }
            is AuthState.Error -> Text((state as AuthState.Error).message, color = Color.Red)
            else -> {}
        }
    }
}