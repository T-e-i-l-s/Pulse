package com.mustafin.local_data_source.data.local.typeConverters

import androidx.room.TypeConverter
import com.mustafin.core.utils.http.HttpResponseStatusModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/* Room converters for ResponseStatusModel class */
class ResponseStatusConverters {
    private val separator = "|"
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromResponseStatusesList(responseStatus: List<com.mustafin.core.utils.http.HttpResponseStatusModel?>?): String {
        return responseStatus?.joinToString(separator = ",") { responseStatusModel ->
            responseStatusModel?.let {
                "${it.statusCode ?: ""}$separator${it.message ?: ""}$separator${
                    it.updatedAt.format(dateTimeFormatter)
                }"
            } ?: ""
        } ?: ""
    }

    @TypeConverter
    fun toResponseStatusesList(value: String): List<com.mustafin.core.utils.http.HttpResponseStatusModel?>? {
        if (value.isEmpty()) return null

        return value.split(",").mapNotNull { item ->
            val parts = item.split(separator)
            if (parts.size == 3) {
                val statusCode = parts[0].toIntOrNull()
                val message = parts[1].takeIf { it.isNotEmpty() }
                val updatedAt = LocalDateTime.parse(parts[2], dateTimeFormatter)
                com.mustafin.core.utils.http.HttpResponseStatusModel(statusCode, message, updatedAt)
            } else {
                null
            }
        }
    }
}
