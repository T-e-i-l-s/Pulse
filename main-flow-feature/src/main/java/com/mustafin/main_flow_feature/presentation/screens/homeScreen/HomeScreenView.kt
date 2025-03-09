package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

/* Home screen composable */
@Composable
fun HomeScreenView(viewModel: HomeScreenViewModel = koinViewModel()) {
    viewModel.hello()
}