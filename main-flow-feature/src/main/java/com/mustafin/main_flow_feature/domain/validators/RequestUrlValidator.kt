package com.mustafin.main_flow_feature.domain.validators

/* Singleton that provides request url validation functions */
object RequestUrlValidator {
    /* Requested format(you can use both http/https): http://justasample/api/v1/ */
    fun validateRequestUrl(requestUrl: String): Boolean {
        val regex = """^https?://([\w.-]+|\d{1,3}(\.\d{1,3}){3})(:\d+)?(/[\w./%-]*)?$""".toRegex()
        return regex.matches(requestUrl)
    }
}