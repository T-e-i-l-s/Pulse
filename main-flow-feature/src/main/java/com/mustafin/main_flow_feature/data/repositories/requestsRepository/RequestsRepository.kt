package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.main_flow_feature.utils.requests.RequestModel

interface RequestsRepository {
    suspend fun getAllRequests(
        onLoad: (List<RequestModel>) -> Unit,
        onUpdate: (List<RequestModel>) -> Unit
    )

    suspend fun addRequest(request: RequestModel)

    suspend fun deleteRequest(requestId: Int)
}