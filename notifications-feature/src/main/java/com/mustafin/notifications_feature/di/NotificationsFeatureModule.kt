package com.mustafin.notifications_feature.di

import android.app.NotificationManager
import android.content.Context
import com.mustafin.notifications_feature.data.notifacations.NotificationChannelManager
import com.mustafin.notifications_feature.presentation.notifications.errorNotification.ErrorNotification
import com.mustafin.notifications_feature.presentation.notifications.errorNotification.ErrorNotificationImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val notificationsFeatureModule = module {
    // General
    single {
        androidContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    // Data layer
    singleOf(::NotificationChannelManager)

    // Presentation layer
    singleOf(::ErrorNotificationImpl) bind ErrorNotification::class
}