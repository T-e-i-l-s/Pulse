package com.mustafin.main_flow_feature.utils.requests

/* Model of а request provided by user */
data class RequestModel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val url: String,
    val requestMethod: RequestMethod,
    val lastResponseStatus: ResponseStatusModel? = null
)
