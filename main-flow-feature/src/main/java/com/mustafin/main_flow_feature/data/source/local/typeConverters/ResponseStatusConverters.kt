package com.mustafin.main_flow_feature.data.source.local.typeConverters

import androidx.room.TypeConverter
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/* Room converters for ResponseStatusModel class */
class ResponseStatusConverters {
    private val separator = "|"
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromResponseStatus(responseStatus: HttpResponseStatusModel?): String {
        return if (responseStatus != null) {
            "${responseStatus.statusCode ?: ""}$separator${responseStatus.message ?: ""}$separator${
                responseStatus.updatedAt.format(
                    dateTimeFormatter
                )
            }"
        } else {
            ""
        }
    }

    @TypeConverter
    fun toResponseStatus(value: String): HttpResponseStatusModel? {
        if (value.isEmpty()) return null

        val parts = value.split(separator)
        if (parts.size != 3) return null

        val statusCode = parts[0].toIntOrNull()
        val message = parts[1].takeIf { it.isNotEmpty() }
        val updatedAt = LocalDateTime.parse(parts[2], dateTimeFormatter)

        return HttpResponseStatusModel(statusCode, message, updatedAt)
    }
}