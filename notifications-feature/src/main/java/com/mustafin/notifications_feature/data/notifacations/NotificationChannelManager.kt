package com.mustafin.notifications_feature.data.notifacations

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.mustafin.notifications_feature.Constants
import com.mustafin.notifications_feature.R

/* A class that provides notification channels and helps manage them */
class NotificationChannelManager(
    private val context: Context,
    private val notificationManager: NotificationManager
) {
    fun initializeErrorNotificationChannel() {
        val channel = NotificationChannel(
            Constants.ERROR_NOTIFICATION_CHANNEL_ID,
            context.getString(R.string.error_notification_channel_name),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
    }
}