package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.main_flow_feature.data.mappers.mapToRequestModel
import com.mustafin.main_flow_feature.data.mappers.mapToRequestsEntity
import com.mustafin.main_flow_feature.data.source.local.requestsSource.RequestsDao
import com.mustafin.main_flow_feature.utils.requests.RequestModel

/* Repository that provides requests created by user */
class RequestsRepositoryImpl(
    private val requestsDao: RequestsDao
) : RequestsRepository {
    override suspend fun getAllRequests(): List<RequestModel> {
        return requestsDao.getAllRequests().map { it.mapToRequestModel() }
    }

    override suspend fun addRequest(request: RequestModel) {
        requestsDao.insertRequest(request.mapToRequestsEntity())
    }

    override suspend fun deleteRequest(requestId: Int) {
        TODO("Not yet implemented")
    }
}