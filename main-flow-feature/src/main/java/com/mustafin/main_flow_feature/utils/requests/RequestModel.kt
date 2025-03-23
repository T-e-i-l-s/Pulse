package com.mustafin.main_flow_feature.utils.requests

import com.mustafin.ping_feature.utils.http.HttpRequestModel
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel

/* Model of а request provided by user */
data class RequestModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val httpRequestInfo: HttpRequestModel,
    val lastResponseStatus: HttpResponseStatusModel? = null,
    val notificationsEnabled: Boolean = true
)
