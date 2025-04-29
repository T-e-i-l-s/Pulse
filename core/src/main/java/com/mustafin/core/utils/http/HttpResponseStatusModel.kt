package com.mustafin.core.utils.http

import java.time.LocalDateTime

/* Model that contains info about server response status */
data class HttpResponseStatusModel(
    var statusCode: Int?,
    var message: String?,
    var updatedAt: LocalDateTime
)
