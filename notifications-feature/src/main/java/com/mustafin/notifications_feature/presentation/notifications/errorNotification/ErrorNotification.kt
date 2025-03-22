package com.mustafin.notifications_feature.presentation.notifications.errorNotification

import com.mustafin.notifications_feature.utils.error.ErrorNotificationModel

interface ErrorNotification {
    fun sendNotification(error: ErrorNotificationModel)
}