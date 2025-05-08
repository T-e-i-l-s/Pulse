package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.core.utils.requests.RequestModel

interface RequestsRepository {
    suspend fun getListOfRequests(): List<RequestModel>

    suspend fun updateResponseStatuses(requests: List<RequestModel>): List<RequestModel>

    suspend fun toggleNotifications(request: RequestModel): RequestModel

    suspend fun addRequest(request: RequestModel)

    suspend fun deleteRequest(requestId: Int)
}