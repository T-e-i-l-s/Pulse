package com.mustafin.pulse

import android.app.Application
import com.mustafin.background_checks_feature.data.repositories.backgroundChecksRepository.BackgroundChecksRepository
import com.mustafin.background_checks_feature.di.backgroundChecksModule
import com.mustafin.main_flow_feature.di.mainFlowModule
import com.mustafin.notifications_feature.data.notifacations.NotificationChannelManager
import com.mustafin.notifications_feature.di.notificationsFeatureModule
import com.mustafin.notifications_feature.presentation.notifications.errorNotification.ErrorNotification
import com.mustafin.notifications_feature.utils.error.ErrorNotificationModel
import com.mustafin.ping_feature.di.pingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.inject

class App : Application() {
    private val backgroundChecksRepository: BackgroundChecksRepository by inject(
        BackgroundChecksRepository::class.java
    )
    private val notificationChannelManager: NotificationChannelManager by inject(
        NotificationChannelManager::class.java
    )

    private val errorNotification: ErrorNotification by inject(
        ErrorNotification::class.java
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(mainFlowModule, pingModule, backgroundChecksModule, notificationsFeatureModule)
        }

        // Creating/restarting background ping work
        backgroundChecksRepository.registerPingWorker()

        // Creating required notification channels
        notificationChannelManager.initializeErrorNotificationChannel()

        errorNotification.sendNotification(
            ErrorNotificationModel(
                "https://mustafin.online/",
                500,
                "Internal Server Error"
            )
        )
    }
}