package com.programacionmovil.citafacil.features.appointments.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programacionmovil.citafacil.features.appointments.domain.usecases.ScheduleAppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleUseCase: ScheduleAppointmentUseCase
) : ViewModel() {

    var doctorName by mutableStateOf("")
    var date by mutableStateOf("")
    var reason by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isSuccess by mutableStateOf(false)

    fun onSchedule() {
        if (doctorName.isBlank() || date.isBlank()) return

        viewModelScope.launch {
            isLoading = true
            scheduleUseCase(doctorName, date, reason)
                .onSuccess { isSuccess = true }
            isLoading = false
        }
    }
}