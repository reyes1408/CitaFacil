package com.programacionmovil.citafacil.features.appointments.presentation

import android.graphics.Bitmap
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
    private val hardwareManager: HardwareManager
) : ViewModel() {

    var doctorName by mutableStateOf("")
    var date by mutableStateOf("")
    var reason by mutableStateOf("")

    var evidencePhoto by mutableStateOf<Bitmap?>(null)

    var isLoading by mutableStateOf(false)
        private set
    var isSuccess by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onSchedule() {
        if (doctorName.isBlank() || date.isBlank() || reason.isBlank()) {
            errorMessage = "Completa todos los campos"
            hardwareManager.vibrate(500)
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            hardwareManager.vibrate(50)

            scheduleUseCase(doctorName, date, reason).onSuccess {

                if (evidencePhoto != null) {
                    hardwareManager.successVibration()
                }
                isSuccess = true
            }
            isLoading = false
        }
    }
}