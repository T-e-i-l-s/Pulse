package com.mustafin.main_flow_feature.data.source.local.requestsSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mustafin.main_flow_feature.data.source.local.db.Tables
import com.mustafin.main_flow_feature.utils.requests.RequestMethod
import com.mustafin.main_flow_feature.utils.requests.ResponseStatusModel

@Entity(tableName = Tables.REQUESTS_TABLE)
data class RequestsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val url: String,
    val requestMethod: RequestMethod,
    val lastResponseStatus: ResponseStatusModel?
)
