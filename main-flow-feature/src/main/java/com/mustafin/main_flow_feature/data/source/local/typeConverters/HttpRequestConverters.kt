package com.mustafin.main_flow_feature.data.source.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mustafin.ping_feature.utils.http.HttpMethod
import com.mustafin.ping_feature.utils.http.HttpRequestModel
import org.koin.java.KoinJavaComponent.inject

/* Room converters for HttpRequestModel class */
class HttpRequestConverters {
    private val gson: Gson by inject(Gson::class.java)

    @TypeConverter
    fun fromHttpRequestModel(model: com.mustafin.ping_feature.utils.http.HttpRequestModel): String {
        val jsonObject = JsonObject()
        jsonObject.addProperty("url", model.url)
        jsonObject.addProperty("httpMethod", model.httpMethod::class.java.simpleName)
        return gson.toJson(jsonObject)
    }

    @TypeConverter
    fun toHttpRequestModel(jsonString: String): com.mustafin.ping_feature.utils.http.HttpRequestModel {
        val jsonObject = JsonParser.parseString(jsonString).asJsonObject
        val url = jsonObject.get("url").asString
        val httpMethod = when (jsonObject.get("httpMethod").asString) {
            "GET" -> com.mustafin.ping_feature.utils.http.HttpMethod.GET
            "POST" -> com.mustafin.ping_feature.utils.http.HttpMethod.POST
            "DELETE" -> com.mustafin.ping_feature.utils.http.HttpMethod.DELETE
            "PUT" -> com.mustafin.ping_feature.utils.http.HttpMethod.PUT
            else -> com.mustafin.ping_feature.utils.http.HttpMethod.PATCH
        }
        return com.mustafin.ping_feature.utils.http.HttpRequestModel(url, httpMethod)
    }
}