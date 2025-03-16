package com.mustafin.ping_feature.data.repositories.pingRepository

import com.mustafin.ping_feature.data.mappers.mapToKtorHttpMethod
import com.mustafin.ping_feature.data.source.UnifiedApiService
import com.mustafin.ping_feature.utils.http.HttpRequestModel
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.url
import java.time.LocalDateTime

/* Repository that executes ping request to user's api */
class PingRepositoryImpl(private val unifiedApiService: UnifiedApiService) : PingRepository {
    override suspend fun ping(request: HttpRequestModel): HttpResponseStatusModel? {
        val customRequest = HttpRequestBuilder().apply {
            method = request.httpMethod.mapToKtorHttpMethod()
            url(request.url)
        }

        val response = unifiedApiService.createCustomRequest(customRequest)

        return response?.let { responseSafe ->
            HttpResponseStatusModel(
                statusCode = responseSafe.status.value,
                message = responseSafe.status.description,
                updatedAt = LocalDateTime.now()
            )
        }
    }
}