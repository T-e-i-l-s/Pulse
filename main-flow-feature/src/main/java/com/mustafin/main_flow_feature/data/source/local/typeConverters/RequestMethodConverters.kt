package com.mustafin.main_flow_feature.data.source.local.typeConverters

import androidx.room.TypeConverter
import com.mustafin.main_flow_feature.utils.requests.RequestMethod

/* Room converters for RequestMethod class */
class RequestMethodConverters {
    @TypeConverter
    fun fromRequestMethod(method: RequestMethod): String {
        return method.toString()
    }

    @TypeConverter
    fun toRequestMethod(value: String): RequestMethod {
        return when (value) {
            "GET" -> RequestMethod.GET
            "POST" -> RequestMethod.POST
            "DELETE" -> RequestMethod.DELETE
            "PUT" -> RequestMethod.PUT
            else -> RequestMethod.PATCH
        }
    }
}