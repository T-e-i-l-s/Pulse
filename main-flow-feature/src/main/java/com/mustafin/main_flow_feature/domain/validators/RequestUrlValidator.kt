package com.mustafin.main_flow_feature.domain.validators

/* Singleton that provides request url validation functions */
object RequestUrlValidator {
    /* Requested format(you can use both http/https): http://justasample/api/v1/ */
    fun validateRequestUrl(requestUrl: String): Boolean {
        val regex = """^https?://[\w.-]+(?:\.[a-zA-Z]{2,})+(:\d+)?(/.*)?/$""".toRegex()
        return regex.matches(requestUrl)
    }
}