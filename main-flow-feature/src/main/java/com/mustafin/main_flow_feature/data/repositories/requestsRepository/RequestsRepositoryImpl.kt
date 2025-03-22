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
        return coroutineScope {
            requests.map {
                async {
                    val newResponseStatus = pingRepository.ping(it.httpRequestInfo)
                    requestsDao.updateResponseStatus(it.id, newResponseStatus)
                    it.copy(lastResponseStatus = newResponseStatus)
                }
            }.awaitAll()
        }
    }

    override suspend fun addRequest(request: RequestModel) {
        // Firstly, executing first request to user's api
        val response = pingRepository.ping(request.httpRequestInfo)
        // Adding information about last server response
        val completedRequestInfo = request.copy(lastResponseStatus = response)
        // Saving
        requestsDao.insertRequest(completedRequestInfo.mapToRequestsEntity())
    }

    override suspend fun deleteRequest(requestId: Int) {
        requestsDao.deleteRequestById(requestId)
    }
}