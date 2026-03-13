package com.programacionmovil.citafacil.features.auth.presentation.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.programacionmovil.citafacil.features.auth.presentation.components.AuthTextField
import com.programacionmovil.citafacil.features.auth.presentation.viewmodels.RegisterViewModel
import com.programacionmovil.citafacil.features.auth.presentation.viewmodels.AuthState

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Crear Cuenta", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        AuthTextField(value = name, onValueChange = { name = it }, label = "Nombre completo")
        AuthTextField(value = email, onValueChange = { email = it }, label = "Correo electrónico")
        AuthTextField(value = password, onValueChange = { password = it }, label = "Contraseña", isPassword = true)

        Button(
            onClick = { viewModel.register(email, password, name) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            enabled = state !is AuthState.Loading
        ) {
            Text("Registrarse")
        }

        TextButton(onClick = onNavigateBack) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }

        // Observar el estado
        when (state) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Success -> {
                LaunchedEffect(Unit) { onRegisterSuccess() }
            }
            is AuthState.Error -> {
                Text(text = (state as AuthState.Error).message, color = Color.Red)
            }
            else -> {}
        }
    }
}