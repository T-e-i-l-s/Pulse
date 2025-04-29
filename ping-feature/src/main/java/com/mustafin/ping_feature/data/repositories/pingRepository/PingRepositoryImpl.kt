package com.mustafin.ping_feature.data.repositories.pingRepository

import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.core.utils.requests.RequestModel
import com.mustafin.local_data_source.data.local.requestsSource.RequestsDao
import com.mustafin.local_data_source.data.mappers.mapToRequestsEntity
import com.mustafin.ping_feature.data.mappers.mapToKtorHttpMethod
import com.mustafin.ping_feature.data.source.UnifiedApiService
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.url
import java.time.LocalDateTime

/* Repository that executes ping request to user's api */
class PingRepositoryImpl(
    private val unifiedApiService: UnifiedApiService,
    private val requestsDao: RequestsDao
) : PingRepository {
    override suspend fun ping(request: RequestModel): HttpResponseStatusModel? {
        val customRequest = HttpRequestBuilder().apply {
            method = request.httpRequestInfo.httpMethod.mapToKtorHttpMethod()
            url(request.httpRequestInfo.url)
        }

        val response = unifiedApiService.createCustomRequest(customRequest)

        val lastResponseStatus = response?.let { responseSafe ->
            HttpResponseStatusModel(
                statusCode = responseSafe.status.value,
                message = responseSafe.status.description,
                updatedAt = LocalDateTime.now()
            )
        }

        val updatedResponseStatusesList = request.responseStatuses + lastResponseStatus
        val updatedRequest = request.copy(responseStatuses = updatedResponseStatusesList)

        println(updatedRequest)
        // Updating values in cache
        requestsDao.insertRequest(updatedRequest.mapToRequestsEntity())

        return lastResponseStatus
    }
}