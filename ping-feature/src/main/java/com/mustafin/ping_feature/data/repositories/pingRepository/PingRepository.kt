package com.mustafin.ping_feature.data.repositories.pingRepository

import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.core.utils.requests.RequestModel

interface PingRepository {
    suspend fun ping(request: RequestModel): HttpResponseStatusModel?
}