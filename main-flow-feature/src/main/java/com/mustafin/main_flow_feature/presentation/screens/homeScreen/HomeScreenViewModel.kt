package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.checkSelfPermission
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
    private val application: Application,
    private val requestsRepository: RequestsRepository
) : ViewModel() {
    private val _loadingState = MutableStateFlow(LoadingState.LOADING)
    val loadingState: StateFlow<LoadingState> = _loadingState

    private val _notificationPermissionWasGranted = MutableStateFlow<Boolean?>(null)
    val notificationPermissionWasGranted: StateFlow<Boolean?> = _notificationPermissionWasGranted

    private val _requests = MutableStateFlow<List<RequestModel>>(emptyList())
    val requests: StateFlow<List<RequestModel>> = _requests

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.LOADING
            _requests.value = requestsRepository.getListOfRequests()
            _loadingState.value = LoadingState.UPDATING
            _requests.value = requestsRepository.updateResponseStatuses(requests.value)
            _loadingState.value = LoadingState.LOADED
        }
    }

    fun toggleNotificationsOnRequest(index: Int) {
        viewModelScope.launch {
            val updatedRequest = requestsRepository.updateRequest(requests.value[index])
            val updatedRequestsList = requests.value.toMutableList()
            updatedRequestsList[index] = updatedRequest
            _requests.value = updatedRequestsList
        }
    }

    fun deleteRequest(requestModel: RequestModel) {
        viewModelScope.launch {
            requestsRepository.deleteRequest(requestModel.id)
            val updatedRequestsList = requests.value.toMutableList()
            updatedRequestsList.removeIf { it.id == requestModel.id }
            _requests.value = updatedRequestsList
        }
    }

    fun onPermissionRequestResult(wasGranted: Boolean) {
        _notificationPermissionWasGranted.value = wasGranted
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun checkNotificationPermissionsStatus() {
        _notificationPermissionWasGranted.value =
            checkSelfPermission(
                application.applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
    }
}