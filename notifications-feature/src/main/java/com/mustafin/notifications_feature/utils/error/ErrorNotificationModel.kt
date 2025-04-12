package com.mustafin.notifications_feature.utils.error

/* A model with information about the error that would be shown in the notification */
data class ErrorNotificationModel(
    val url: String,
    val requestMethod: String,
    val statusCode: Int?,
    val message: String?
)
