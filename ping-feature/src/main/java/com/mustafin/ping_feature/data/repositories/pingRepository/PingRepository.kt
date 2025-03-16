package com.mustafin.ping_feature.data.repositories.pingRepository

import com.mustafin.ping_feature.utils.http.HttpRequestModel
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel

interface PingRepository {
    suspend fun ping(request: HttpRequestModel): HttpResponseStatusModel?
}