package com.mustafin.notifications_feature.presentation.notifications.errorNotification

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mustafin.notifications_feature.NotificationConstants
import com.mustafin.notifications_feature.R
import com.mustafin.notifications_feature.utils.error.ErrorNotificationModel

/* A class that creates and manages error notifications */
class ErrorNotificationImpl(private val context: Context) : ErrorNotification {
    @SuppressLint("MissingPermission")
    override fun sendNotification(error: ErrorNotificationModel) {
        val builder = NotificationCompat.Builder(
            context,
            NotificationConstants.ERROR_NOTIFICATION_CHANNEL_ID
        ).apply {
            setContentTitle(context.getString(R.string.error_notification_title))
            setContentText(
                context.getString(
                    R.string.error_notification_text,
                    error.url,
                    error.statusCode,
                    error.message
                )
            )
            setSmallIcon(R.drawable.error_icon)
        }

        NotificationManagerCompat.from(context).notify(1, builder.build())
    }
}