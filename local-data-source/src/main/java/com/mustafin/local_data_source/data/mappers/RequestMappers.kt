package com.mustafin.local_data_source.data.mappers

import com.mustafin.core.utils.requests.RequestModel
import com.mustafin.local_data_source.data.local.requestsSource.RequestsEntity

/* Mappers for request data models */

fun RequestsEntity.mapToRequestModel(): RequestModel {
    return RequestModel(
        id = id,
        title = title,
        description = description,
        httpRequestInfo = httpRequestInfo,
        responseStatuses = responseStatuses,
        notificationsEnabled = notificationsEnabled
    )
}

fun RequestModel.mapToRequestsEntity(): RequestsEntity {
    return RequestsEntity(
        id = id,
        title = title,
        description = description,
        httpRequestInfo = httpRequestInfo,
        responseStatuses = responseStatuses,
        notificationsEnabled = notificationsEnabled
    )
}