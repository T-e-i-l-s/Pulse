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
import com.mustafin.ui_components.presentation.vibration.CustomVibrationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/* View model of home screen */
class HomeScreenViewModel(
    private val application: Application,
    private val vibrationManager: CustomVibrationManager,
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
            updateData()
        }
    }

    fun updateData() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.UPDATING
            _requests.value = requestsRepository.updateResponseStatuses(requests.value)
            _loadingState.value = LoadingState.LOADED
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

    // Delete and toggle notifications features implementation

    private var lastRequestIndexToDisableNotifications: Int? = null

    private val _showConfirmDisableNotificationsDialog = MutableStateFlow(false)
    val showConfirmDisableNotificationsDialog: StateFlow<Boolean> =
        _showConfirmDisableNotificationsDialog

    private var lastRequestIndexToDelete: Int? = null

    private val _showConfirmDeleteRequestDialog = MutableStateFlow(false)
    val showConfirmDeleteRequestDialog: StateFlow<Boolean> = _showConfirmDeleteRequestDialog


    fun toggleNotifications(index: Int) {
        lastRequestIndexToDisableNotifications = index
        if (requests.value[index].notificationsEnabled) {
            /*
            If notifications are enabled, we prompt the user for confirmation before disabling them.
            */
            _showConfirmDisableNotificationsDialog.value = true
        } else {
            /*
            If notifications are disabled, we enable them directly without asking the user
            for confirmation, as there's no need to confirm enabling them.
            */
            confirmToggleNotificationsRequest()
        }
    }

    fun confirmToggleNotificationsRequest() {
        lastRequestIndexToDisableNotifications?.let { requestIndexSafe ->
            viewModelScope.launch {
                val updatedRequest =
                    requestsRepository.toggleNotifications(requests.value[requestIndexSafe])
                val updatedRequestsList = requests.value.toMutableList()
                updatedRequestsList[requestIndexSafe] = updatedRequest
                _requests.value = updatedRequestsList

                // Vibrating
                vibrationManager.shortSingleVibration()
            }
        }
        _showConfirmDisableNotificationsDialog.value = false
    }

    fun denyConfirmToggleNotificationsRequest() {
        _showConfirmDisableNotificationsDialog.value = false
    }

    fun deleteRequest(index: Int) {
        // Asking user's permission to delete provided request
        _showConfirmDeleteRequestDialog.value = true
        lastRequestIndexToDelete = index
    }

    fun confirmDeleteRequest() {
        lastRequestIndexToDelete?.let { requestIndexSafe ->
            viewModelScope.launch {
                requestsRepository.deleteRequest(requests.value[requestIndexSafe].id)
                val updatedRequestsList = requests.value.toMutableList()
                updatedRequestsList.removeAt(requestIndexSafe)
                _requests.value = updatedRequestsList

                // Vibrating
                vibrationManager.shortSingleVibration()
            }
        }
        _showConfirmDeleteRequestDialog.value = false
    }

    fun denyDeleteRequest() {
        _showConfirmDeleteRequestDialog.value = false
    }
}