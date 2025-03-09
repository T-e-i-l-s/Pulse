package com.mustafin.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.HomeScreenView

/* Main navigation graph */
@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.HomeScreen) {
        composable<Destinations.HomeScreen> {
            HomeScreenView()
        }
    }
}