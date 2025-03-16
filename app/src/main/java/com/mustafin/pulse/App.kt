package com.mustafin.pulse

import android.app.Application
import com.mustafin.main_flow_feature.di.mainFlowModule
import com.mustafin.ping_feature.di.pingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(mainFlowModule, pingModule)
        }
    }
}