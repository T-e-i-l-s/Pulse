package com.mustafin.local_data_source.data.local.requestsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.core.utils.http.HttpRequestModel
import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.local_data_source.data.local.db_core.Tables

@Entity(tableName = Tables.REQUESTS_TABLE)
data class RequestsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val httpRequestInfo: HttpRequestModel,
    val responseStatuses: List<HttpResponseStatusModel?>,
    val notificationsEnabled: Boolean
)
