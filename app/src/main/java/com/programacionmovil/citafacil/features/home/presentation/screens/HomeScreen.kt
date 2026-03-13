package com.programacionmovil.citafacil.features.home.presentation.screens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido de nuevo,",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = viewModel.userName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {  }) {
            Text("Explorar mis citas")
        }
    }
}