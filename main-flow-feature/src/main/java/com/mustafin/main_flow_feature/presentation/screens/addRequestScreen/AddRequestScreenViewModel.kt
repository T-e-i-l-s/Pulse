package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepository
import com.mustafin.main_flow_feature.utils.requests.RequestMethod
import com.mustafin.main_flow_feature.utils.requests.RequestModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/* View model of create new request screen */
class AddRequestScreenViewModel(private val requestsRepository: RequestsRepository) : ViewModel() {
    private val _isCreationEnabled = MutableStateFlow(false)
    val isCreationEnabled: StateFlow<Boolean> = _isCreationEnabled

    private val _selectedRequestMethod = MutableStateFlow<RequestMethod?>(null)
    val selectedRequestMethod: StateFlow<RequestMethod?> = _selectedRequestMethod

    private val _requestUrl = MutableStateFlow("")
    val requestUrl: StateFlow<String> = _requestUrl

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description

    fun selectRequestMethod(requestMethod: RequestMethod) {
        _selectedRequestMethod.value =
            if (selectedRequestMethod.value == requestMethod) null else requestMethod
        checkIsCreationEnabled()
    }

    fun setRequestUrl(value: String) {
        _requestUrl.value = value
        checkIsCreationEnabled()
    }

    fun setTitle(value: String) {
        _title.value = value
        checkIsCreationEnabled()
    }

    fun setDescription(value: String) {
        _description.value = value
    }

    private fun checkIsCreationEnabled() {
        _isCreationEnabled.value =
            selectedRequestMethod.value != null && requestUrl.value.isNotBlank() && title.value.isNotBlank()
    }

    fun createRequest() {
        viewModelScope.launch {
            selectedRequestMethod.value?.let { requestMethodSafe ->
                requestsRepository.addRequest(
                    RequestModel(
                        title = title.value,
                        description = description.value,
                        requestMethod = requestMethodSafe,
                        url = requestUrl.value
                    )
                )
            }
        }
    }
}