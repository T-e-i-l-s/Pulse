package com.mustafin.main_flow_feature.data.source.local.requestsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.main_flow_feature.data.source.local.db.Tables
import com.mustafin.ping_feature.utils.http.HttpRequestModel

@Entity(tableName = Tables.REQUESTS_TABLE)
data class RequestsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val httpRequestInfo: HttpRequestModel,
    val lastResponseStatus: com.mustafin.ping_feature.utils.http.HttpResponseStatusModel?
)
