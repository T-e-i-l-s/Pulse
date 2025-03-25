package com.mustafin.main_flow_feature.utils.time

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/* Utilities that provide some helpful features for the LocalDateTime class */

fun LocalDateTime.toSimpleTimeString(): String {
    return this.format(DateTimeFormatter.ofPattern("HH:mm"))
}