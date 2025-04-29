package com.mustafin.core.utils.http

import com.mustafin.core.R

/* All request methods, that enabled in the application */
sealed class HttpMethod(val colorRes: Int) {
    data object GET : HttpMethod(R.color.blue)
    data object POST : HttpMethod(R.color.green)
    data object DELETE : HttpMethod(R.color.red)
    data object PUT : HttpMethod(R.color.yellow)
    data object PATCH : HttpMethod(R.color.yellow)
}