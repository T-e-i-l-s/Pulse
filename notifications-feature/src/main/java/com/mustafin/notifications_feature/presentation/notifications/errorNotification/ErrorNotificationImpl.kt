package com.mustafin.notifications_feature.presentation.notifications.errorNotification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
                        it.statusCode ?: "",
                        it.message ?: context.getString(R.string.service_unavailable)
                    )
                }
            ))
            setSmallIcon(R.drawable.error_icon)

            // Creating intent to open app on click
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("pulse://root"))
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            setContentIntent(pendingIntent)
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