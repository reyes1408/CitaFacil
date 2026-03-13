package com.programacionmovil.citafacil.features.appointments.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programacionmovil.citafacil.core.common.HardwareManager
import com.programacionmovil.citafacil.features.appointments.domain.usecases.ScheduleAppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleAppointmentUseCase,
    private val hardwareManager: HardwareManager // Inyectamos el control de hardware
) : ViewModel() {

    // Estados de los campos del formulario
    var doctorName by mutableStateOf("")
    var date by mutableStateOf("")
    var reason by mutableStateOf("")

    // Estados de control de la interfaz
    var isLoading by mutableStateOf(false)
        private set

    var isSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onSchedule() {
        // Validación básica
        if (doctorName.isBlank() || date.isBlank() || reason.isBlank()) {
            errorMessage = "Por favor, completa todos los campos"
            hardwareManager.vibrate(500) // Vibración larga de advertencia/error
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            // Vibración sutil al iniciar el proceso (Feedback táctil)
            hardwareManager.vibrate(50)

            scheduleUseCase(doctorName, date, reason)
                .onSuccess {
                    // ¡ÉXITO! Vibración doble de confirmación
                    hardwareManager.successVibration()
                    isSuccess = true
                }
                .onFailure { exception ->
                    // ERROR: Vibración de error
                    hardwareManager.vibrate(500)
                    errorMessage = exception.message ?: "Error desconocido al agendar"
                }

            isLoading = false
        }
    }
}