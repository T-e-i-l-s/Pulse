package com.mustafin.local_data_source.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.core.utils.time.LocalDateTimeGsonAdapter
import java.lang.reflect.Type
import java.time.LocalDateTime

/* Room converters for ResponseStatusModel class */
class ResponseStatusConverters {
    private val gson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeGsonAdapter())
            .create()
    }

    @TypeConverter
    fun fromResponseStatusesList(responseStatus: List<HttpResponseStatusModel?>?): String {
        return gson.toJson(responseStatus)
    }

    @TypeConverter
    fun toResponseStatusesList(value: String): List<HttpResponseStatusModel?>? {
        val listType: Type = object : TypeToken<List<HttpResponseStatusModel?>>() {}.type
        return gson.fromJson(value, listType)
    }
}