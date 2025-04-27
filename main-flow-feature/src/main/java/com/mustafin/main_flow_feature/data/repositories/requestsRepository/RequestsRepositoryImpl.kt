package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.local_data_source.data.local.requestsSource.RequestsDao
import com.mustafin.main_flow_feature.data.mappers.mapToRequestModel
import com.mustafin.main_flow_feature.data.mappers.mapToRequestsEntity
import com.mustafin.main_flow_feature.utils.requests.RequestModel
import com.mustafin.ping_feature.data.repositories.pingRepository.PingRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

/* Repository that provides requests created by user */
class RequestsRepositoryImpl(
    private val requestsDao: RequestsDao,
    private val pingRepository: PingRepository
) : RequestsRepository {
    override suspend fun getListOfRequests(): List<RequestModel> {
        return requestsDao.getAllRequests().map { it.mapToRequestModel() }
    }

    override suspend fun updateResponseStatuses(requests: List<RequestModel>): List<RequestModel> {
        // Asynchronously executing all requests and saving new response statuses
        return coroutineScope {
            requests.map { request ->
                async {
                    val newResponseStatus = pingRepository.ping(request.httpRequestInfo)
                    val updatedRequest =
                        request.copy(responseStatuses = request.responseStatuses + newResponseStatus)
                    requestsDao.insertRequest(updatedRequest.mapToRequestsEntity())
                    updatedRequest
                }
            }.awaitAll()
        }
    }

    override suspend fun toggleNotifications(request: RequestModel): RequestModel {
        val updatedRequest = request.copy(notificationsEnabled = !request.notificationsEnabled)
        requestsDao.insertRequest(updatedRequest.mapToRequestsEntity())
        return updatedRequest
    }

    override suspend fun addRequest(request: RequestModel) {
        // Firstly, executing first request to user's api
        val response = pingRepository.ping(request.httpRequestInfo)
        // Adding information about last server response
        val completedRequestInfo =
            request.copy(responseStatuses = request.responseStatuses + response)
        // Saving
        requestsDao.insertRequest(completedRequestInfo.mapToRequestsEntity())
    }

    override suspend fun deleteRequest(requestId: Int) {
        requestsDao.deleteRequestById(requestId)
    }
}