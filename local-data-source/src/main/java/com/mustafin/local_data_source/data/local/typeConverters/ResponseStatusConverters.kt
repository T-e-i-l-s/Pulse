package com.mustafin.local_data_source.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
import org.koin.java.KoinJavaComponent.inject

/* Room converters for ResponseStatusModel class */
class ResponseStatusConverters {
    private val gson: Gson by inject(Gson::class.java)

    @TypeConverter
    fun fromResponseStatusesList(responseStatus: List<HttpResponseStatusModel>?): String {
        return gson.toJson(responseStatus)
    }

    @TypeConverter
    fun toResponseStatusesList(value: String): List<HttpResponseStatusModel>? {
        val type = object : TypeToken<List<HttpResponseStatusModel>?>() {}.type
        return gson.fromJson(value, type)
    }
}