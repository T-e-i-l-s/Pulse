package com.mustafin.background_checks_feature.data.workManager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/* Worker that checks servers status, using PingRepository (ping-feature module) */
class PingWorker(
    appContext: Context, workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        println("Just do it!")
        return Result.success()
    }
}