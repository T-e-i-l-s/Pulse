package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import androidx.lifecycle.ViewModel
import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepository

/* View model of home screen */
class HomeScreenViewModel(
    private val requestsRepository: RequestsRepository
) : ViewModel() {
    fun hello() {
        println("hello")
    }
}