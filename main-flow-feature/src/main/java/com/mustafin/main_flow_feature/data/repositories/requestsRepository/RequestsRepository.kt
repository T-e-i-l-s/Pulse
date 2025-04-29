package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.core.utils.requests.RequestModel

interface RequestsRepository {
    suspend fun getListOfRequests(): List<com.mustafin.core.utils.requests.RequestModel>

    suspend fun updateResponseStatuses(requests: List<com.mustafin.core.utils.requests.RequestModel>): List<com.mustafin.core.utils.requests.RequestModel>

    suspend fun toggleNotifications(request: com.mustafin.core.utils.requests.RequestModel): com.mustafin.core.utils.requests.RequestModel

    suspend fun addRequest(request: com.mustafin.core.utils.requests.RequestModel)

    suspend fun deleteRequest(requestId: Int)
}