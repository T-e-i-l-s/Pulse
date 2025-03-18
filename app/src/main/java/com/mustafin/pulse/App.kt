package com.mustafin.pulse

import android.app.Application
import com.mustafin.background_checks_feature.data.repositories.backgroundChecksRepository.BackgroundChecksRepository
import com.mustafin.background_checks_feature.di.backgroundChecksModule
import com.mustafin.main_flow_feature.di.mainFlowModule
import com.mustafin.ping_feature.di.pingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.inject

class App : Application() {
    private val backgroundChecksRepository: BackgroundChecksRepository by inject(
        BackgroundChecksRepository::class.java
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(mainFlowModule, pingModule, backgroundChecksModule)
        }

        // Creating/restarting background ping work
        backgroundChecksRepository.register()
    }
}