package com.example.lab_7

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun FormulaZeroApp() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeScreen(navController) }
            composable("races") { UpcomingRacesScreen(navController) }
            composable("drivers") { DriversScreen(navController) }
        }
    }
}
