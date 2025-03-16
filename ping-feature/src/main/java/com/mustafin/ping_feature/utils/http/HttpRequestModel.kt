package com.mustafin.ping_feature.utils.http

/* A data class containing everything necessary for executing an http request */
data class HttpRequestModel(
    val url: String,
    val httpMethod: HttpMethod
)
