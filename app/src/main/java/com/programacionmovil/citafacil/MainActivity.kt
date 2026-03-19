package com.programacionmovil.citafacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.programacionmovil.citafacil.core.common.HardwareManager
import com.programacionmovil.citafacil.core.navigation.NavGraph
import com.programacionmovil.citafacil.core.theme.CitaFacilTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var hardwareManager: HardwareManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CitaFacilTheme {
                NavGraph(
                    navController = navController,
                    hardwareManager = hardwareManager
                )
            }
        }
    }
}