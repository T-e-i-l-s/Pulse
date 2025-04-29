package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.core.utils.http.HttpMethod
import com.mustafin.core.utils.http.HttpRequestModel
import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepository
import com.mustafin.main_flow_feature.domain.validators.RequestUrlValidator
import com.mustafin.ui_components.presentation.vibration.CustomVibrationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/* View model for the "Create New Request screen */
class AddRequestScreenViewModel(
    private val requestsRepository: RequestsRepository,
    private val vibrationManager: CustomVibrationManager
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isCreationEnabled = MutableStateFlow(false)
    val isCreationEnabled: StateFlow<Boolean> = _isCreationEnabled

    private val _selectedRequestMethod = MutableStateFlow<HttpMethod?>(null)
    val selectedRequestMethod: StateFlow<HttpMethod?> = _selectedRequestMethod

    private val _requestUrl = MutableStateFlow("https://")
    val requestUrl: StateFlow<String> = _requestUrl

    private val _showRequestUrlValidationError = MutableStateFlow(false)
    val showRequestUrlValidationError: StateFlow<Boolean> = _showRequestUrlValidationError

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    private val _notificationsEnabled = MutableStateFlow(true)
    val notificationsEnabled: StateFlow<Boolean> = _notificationsEnabled

    fun selectRequestMethod(requestMethod: HttpMethod) {
        if (isLoading.value) return
        _selectedRequestMethod.value =
            if (selectedRequestMethod.value == requestMethod) null else requestMethod
        checkIsCreationEnabled()
        vibrationManager.shortSingleVibration()
    }

    fun setRequestUrl(value: String) {
        _requestUrl.value = value
        _showRequestUrlValidationError.value = false
        checkIsCreationEnabled()
    }

    fun setTitle(value: String) {
        _title.value = value
        checkIsCreationEnabled()
    }

    fun setDescription(value: String) {
        _description.value = value
    }

    fun setNotificationsEnabled(value: Boolean) {
        _notificationsEnabled.value = value
        vibrationManager.shortSingleVibration()
    }

    private fun checkIsCreationEnabled() {
        _isCreationEnabled.value =
            selectedRequestMethod.value != null && requestUrl.value.isNotBlank() && title.value.isNotBlank()
    }

    fun createRequest(navigateToHomeScreen: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true

            val requestUrlValidationResult =
                RequestUrlValidator.validateRequestUrl(requestUrl.value)
            if (!requestUrlValidationResult) {
                _showRequestUrlValidationError.value = true
                _isLoading.value = false
                return@launch
            }

            selectedRequestMethod.value?.let { requestMethodSafe ->
                requestsRepository.addRequest(
                    com.mustafin.core.utils.requests.RequestModel(
                        title = title.value,
                        description = description.value,
                        httpRequestInfo = HttpRequestModel(
                            url = requestUrl.value,
                            httpMethod = requestMethodSafe
                        ),
                        notificationsEnabled = notificationsEnabled.value
                    )
                )
            }

            navigateToHomeScreen()
            _isLoading.value = false
        }
    }
}