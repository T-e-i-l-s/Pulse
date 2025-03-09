package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepository
import com.mustafin.main_flow_feature.utils.loading.LoadingState
import com.mustafin.main_flow_feature.utils.requests.RequestModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/* View model of home screen */
class HomeScreenViewModel(
    private val requestsRepository: RequestsRepository
) : ViewModel() {
    private val _loadingState = MutableStateFlow(LoadingState.LOADING)
    val loadingState: StateFlow<LoadingState> = _loadingState

    private val _requests = MutableStateFlow<List<RequestModel>?>(null)
    val requests: StateFlow<List<RequestModel>?> = _requests

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            _requests.value = requestsRepository.getAllRequests()
            _loadingState.value = LoadingState.LOADED
        }
    }
}