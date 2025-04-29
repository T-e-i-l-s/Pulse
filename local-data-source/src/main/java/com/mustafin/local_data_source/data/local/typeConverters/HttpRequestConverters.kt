package com.mustafin.local_data_source.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mustafin.core.utils.http.HttpMethod
import com.mustafin.core.utils.http.HttpRequestModel
import org.koin.java.KoinJavaComponent.inject

/* Room converters for HttpRequestModel class */
class HttpRequestConverters {
    private val gson: Gson by inject(Gson::class.java)

    @TypeConverter
    fun fromHttpRequestModel(model: HttpRequestModel): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("url", model.url)
        jsonObject.addProperty("httpMethod", model.httpMethod::class.java.simpleName)
        return gson.toJson(jsonObject)
    }

    @TypeConverter
    fun toHttpRequestModel(jsonString: String): HttpRequestModel {
        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        val url = jsonObject.get("url").asString
        val httpMethod = when (jsonObject.get("httpMethod").asString) {
            "GET" -> HttpMethod.GET
            "POST" -> HttpMethod.POST
            "DELETE" -> HttpMethod.DELETE
            "PUT" -> HttpMethod.PUT
            else -> HttpMethod.PATCH
        }
        return HttpRequestModel(url, httpMethod)
    }
}