package com.mustafin.ping_feature.data.source

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

/* ApiService that provides function which executes a custom http request */
class UnifiedApiService(private val httpClient: HttpClient) {
    suspend fun createCustomRequest(request: HttpRequestBuilder): HttpResponse? {
        return try {
            httpClient.request(request)
        } catch (e: Exception) {
            null
        }
    }
}