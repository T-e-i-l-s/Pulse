package com.mustafin.main_flow_feature.utils.requests

import com.mustafin.main_flow_feature.R

/* All request methods, that enabled in the application */
sealed class RequestMethod(val colorRes: Int) {
    data object GET : RequestMethod(R.color.gray)
    data object POST : RequestMethod(R.color.green)
    data object DELETE : RequestMethod(R.color.red)
    data object PUT : RequestMethod(R.color.yellow)
    data object PATCH : RequestMethod(R.color.yellow)
}