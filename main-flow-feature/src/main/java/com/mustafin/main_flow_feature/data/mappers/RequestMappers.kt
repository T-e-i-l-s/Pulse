package com.mustafin.main_flow_feature.data.mappers

import com.mustafin.local_data_source.data.local.requestsSource.RequestsEntity
import com.mustafin.main_flow_feature.utils.requests.RequestModel

/* Mappers for request data models */

fun RequestsEntity.mapToRequestModel(): RequestModel {
    return RequestModel(
        id = id,
        title = title,
        description = description,
        httpRequestInfo = httpRequestInfo,
        lastResponseStatus = lastResponseStatus,
        notificationsEnabled = notificationsEnabled
    )
}

fun RequestModel.mapToRequestsEntity(): RequestsEntity {
    return RequestsEntity(
        id = id,
        title = title,
        description = description,
        httpRequestInfo = httpRequestInfo,
        lastResponseStatus = lastResponseStatus,
        notificationsEnabled = notificationsEnabled
    )
}