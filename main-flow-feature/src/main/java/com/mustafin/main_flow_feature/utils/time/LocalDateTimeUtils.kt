package com.mustafin.main_flow_feature.utils.time

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toSimpleTimeString(): String {
    return this.format(DateTimeFormatter.ofPattern("HH:mm"))
}