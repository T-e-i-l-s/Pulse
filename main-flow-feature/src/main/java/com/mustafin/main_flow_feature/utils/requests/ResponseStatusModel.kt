package com.mustafin.main_flow_feature.utils.requests

import java.time.LocalDateTime

/* Model that contains info about server response status */
data class ResponseStatusModel(
    val statusCode: Int,
    val message: String,
    val updatedAt: LocalDateTime
)
