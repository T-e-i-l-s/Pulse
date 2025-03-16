package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.main_flow_feature.data.mappers.mapToRequestModel
import com.mustafin.main_flow_feature.data.mappers.mapToRequestsEntity
import com.mustafin.main_flow_feature.data.source.local.requestsSource.RequestsDao
import com.mustafin.main_flow_feature.utils.requests.RequestModel
import com.mustafin.ping_feature.data.repositories.pingRepository.PingRepository

/* Repository that provides requests created by user */
class RequestsRepositoryImpl(
    private val requestsDao: RequestsDao,
    private val pingRepository: PingRepository
) : RequestsRepository {
    override suspend fun getAllRequests(
        onLoad: (List<RequestModel>) -> Unit,
        onUpdate: (List<RequestModel>) -> Unit
    ) {
        println("123")
        println(requestsDao.getAllRequests())
        val allRequests = requestsDao.getAllRequests().map { it.mapToRequestModel() }
//        onLoad(allRequests)
        println("123 " + allRequests)

        allRequests.forEach { request ->
            val newResponseStatus = pingRepository.ping(request.httpRequestInfo)
            requestsDao.updateResponseStatus(request.id, newResponseStatus)
        }
        val updatedRequests = requestsDao.getAllRequests().map { it.mapToRequestModel() }

        println("123 " + updatedRequests)

        onUpdate(updatedRequests)
    }

    override suspend fun addRequest(request: RequestModel) {
        // Firstly, executing first request to user's api
        val response = pingRepository.ping(request.httpRequestInfo)

        val completedRequestInfo = request.copy(lastResponseStatus = response)

        // Saving
        requestsDao.insertRequest(completedRequestInfo.mapToRequestsEntity())
    }

    override suspend fun deleteRequest(requestId: Int) {
        TODO("Not yet implemented")
    }
}