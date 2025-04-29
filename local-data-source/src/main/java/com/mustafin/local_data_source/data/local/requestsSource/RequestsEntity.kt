package com.mustafin.local_data_source.data.local.requestsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.core.utils.http.HttpRequestModel
import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.local_data_source.data.local.db.Tables

@Entity(tableName = Tables.REQUESTS_TABLE)
data class RequestsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val httpRequestInfo: com.mustafin.core.utils.http.HttpRequestModel,
    val responseStatuses: List<com.mustafin.core.utils.http.HttpResponseStatusModel?>,
    val notificationsEnabled: Boolean
)
