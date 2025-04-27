package com.mustafin.local_data_source.data.local.requestsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.local_data_source.data.local.db.Tables
import com.mustafin.ping_feature.utils.http.HttpRequestModel
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel

@Entity(tableName = Tables.REQUESTS_TABLE)
data class RequestsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val httpRequestInfo: HttpRequestModel,
    val responseStatuses: List<HttpResponseStatusModel?>,
    val notificationsEnabled: Boolean
)
