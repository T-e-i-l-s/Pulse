package com.mustafin.notifications_feature.presentation.notifications.errorNotification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mustafin.notifications_feature.NotificationConstants
import com.mustafin.notifications_feature.R
import com.mustafin.notifications_feature.utils.error.ErrorNotificationModel

/* A class that creates and manages error notifications */
class ErrorNotificationImpl(private val context: Context) : ErrorNotification {
    override fun sendNotification(errors: List<ErrorNotificationModel>) {
        val builder = NotificationCompat.Builder(
            context,
            NotificationConstants.ERROR_NOTIFICATION_CHANNEL_ID
        ).apply {
            setContentTitle(context.getString(R.string.error_notification_title))
            setStyle(NotificationCompat.BigTextStyle().bigText(
                errors.joinToString("\n") {
                    context.getString(
                        R.string.error_notification_text,
                        it.requestMethod,
                        it.url,
                        it.statusCode,
                        it.message
                    )
                }
            ))
            setSmallIcon(R.drawable.error_icon)
        }

        if (
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(context).notify(1, builder.build())
        }
    }
}