package com.mustafin.background_checks_feature.data.workManager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.core.utils.requests.RequestModel
import com.mustafin.local_data_source.data.local.requestsSource.RequestsDao
import com.mustafin.local_data_source.data.mappers.mapToRequestModel
import com.mustafin.notifications_feature.presentation.notifications.errorNotification.ErrorNotification
import com.mustafin.notifications_feature.utils.error.ErrorNotificationModel
import com.mustafin.ping_feature.data.repositories.pingRepository.PingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/* Worker that checks servers status, using PingRepository (ping-feature module) */
class PingWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : Worker(appContext, workerParams) {
    private val pingRepository: PingRepository by inject(PingRepository::class.java)
    private val requestsDao: RequestsDao by inject(RequestsDao::class.java)
    private val errorNotification: ErrorNotification by inject(ErrorNotification::class.java)

    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            val requests = requestsDao.getAllRequests()
            val errors = mutableListOf<ErrorNotificationModel>()

            /* Check all statuses asynchronously */
            coroutineScope {
                requests.map { request ->
                    async {
                        handleRequest(
                            request = request.mapToRequestModel(),
                            onError = errors::add
                        )
                    }
                }.awaitAll()
            }

            if (errors.isNotEmpty()) errorNotification.sendNotification(errors)
        }
        return Result.success()
    }

    /* Function checks status of single service */
    private suspend fun handleRequest(
        request: RequestModel,
        onError: (ErrorNotificationModel) -> Unit
    ) {
        suspend fun checkAnService(request: RequestModel) = pingRepository.ping(request)

        var updatedResponseStatus = checkAnService(request)

        if (updatedResponseStatus == null) {
            // Trying again to be sure that smth is really wrong
            updatedResponseStatus = checkAnService(request)
        }

        val statusCode = updatedResponseStatus?.statusCode
        if ((statusCode == null || statusCode >= 400) && request.notificationsEnabled) {
            onError(
                ErrorNotificationModel(
                    url = request.httpRequestInfo.url,
                    requestMethod = request.httpRequestInfo.httpMethod.toString(),
                    statusCode = updatedResponseStatus?.statusCode,
                    message = updatedResponseStatus?.message
                )
            )
        }
    }

}