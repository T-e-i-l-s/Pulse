package com.mustafin.ping_feature.data.mappers

import com.mustafin.core.utils.http.HttpMethod

fun HttpMethod.mapToKtorHttpMethod(): io.ktor.http.HttpMethod {
    return when (this) {
        HttpMethod.GET -> io.ktor.http.HttpMethod.Get
        HttpMethod.POST -> io.ktor.http.HttpMethod.Post
        HttpMethod.DELETE -> io.ktor.http.HttpMethod.Delete
        HttpMethod.PUT -> io.ktor.http.HttpMethod.Put
        HttpMethod.PATCH -> io.ktor.http.HttpMethod.Patch
    }
}