package com.programacionmovil.citafacil.features.home.presentation

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            val result = getUserDataUseCase()
            result.onSuccess { user ->
                userName = user?.name ?: "Usuario"
            }
        }
    }
}