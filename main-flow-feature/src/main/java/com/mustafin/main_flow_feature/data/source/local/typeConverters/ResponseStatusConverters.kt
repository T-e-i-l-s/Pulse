package com.mustafin.main_flow_feature.data.source.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mustafin.main_flow_feature.utils.requests.ResponseStatusModel
import org.koin.java.KoinJavaComponent.inject

/* Room converters for ResponseStatusModel class */
class ResponseStatusConverters {
    private val gson: Gson by inject(Gson::class.java)

    private val type = object : TypeToken<ResponseStatusModel?>() {}.type

    @TypeConverter
    fun fromResponseStatus(responseStatus: ResponseStatusModel?): String {
        return gson.toJson(responseStatus)
    }

    @TypeConverter
    fun toResponseStatus(value: String): ResponseStatusModel? {
        return gson.fromJson(value, type)
    }
}