package com.mustafin.background_checks_feature.data.repositories.backgroundChecksRepository

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.mustafin.background_checks_feature.data.workManager.workers.PingWorker
import java.util.concurrent.TimeUnit

/* Repository that helps to create ping background work */
class BackgroundChecksRepositoryImpl(private val context: Context) : BackgroundChecksRepository {
    override fun registerPingWorker() {
        val request = PeriodicWorkRequestBuilder<PingWorker>(15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "ping_worker",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            request
        )
    }
}