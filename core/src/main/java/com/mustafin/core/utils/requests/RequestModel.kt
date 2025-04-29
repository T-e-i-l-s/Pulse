package com.mustafin.core.utils.requests

import com.mustafin.core.utils.http.HttpRequestModel
import com.mustafin.core.utils.http.HttpResponseStatusModel


/* Model of а request provided by user */
data class RequestModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val httpRequestInfo: HttpRequestModel,
    val responseStatuses: List<HttpResponseStatusModel?> = emptyList(),
    val notificationsEnabled: Boolean = true
)
