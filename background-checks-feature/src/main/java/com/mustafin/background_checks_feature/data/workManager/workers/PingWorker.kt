package com.mustafin.background_checks_feature.data.workManager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mustafin.local_data_source.data.local.requestsSource.RequestsDao
import com.mustafin.local_data_source.data.local.requestsSource.RequestsEntity
import com.mustafin.notifications_feature.presentation.notifications.errorNotification.ErrorNotification
import com.mustafin.notifications_feature.utils.error.ErrorNotificationModel
import com.mustafin.ping_feature.data.repositories.pingRepository.PingRepository
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
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

            coroutineScope {
                requests.map { request ->
                    async {
                        var updatedResponseStatus = checkAnService(request)

                        if (updatedResponseStatus == null) {
                            // Trying again to be sure that smth is really wrong
                            updatedResponseStatus = checkAnService(request)
                        }

                        val updatedResponseStatusesList = request.responseStatuses.toMutableList()
                        updatedResponseStatusesList.add(updatedResponseStatus)
                        val updatedRequest =
                            request.copy(responseStatuses = updatedResponseStatusesList)

                        // Updating values in cache
                        requestsDao.insertRequest(updatedRequest)

                        val lastResponseStatus = updatedResponseStatus
                        val statusCode = lastResponseStatus?.statusCode
                        if ((statusCode == null || statusCode >= 400) && request.notificationsEnabled) {
                            errors.add(
                                ErrorNotificationModel(
                                    request.httpRequestInfo.url,
                                    request.httpRequestInfo.httpMethod.toString(),
                                    lastResponseStatus?.statusCode,
                                    lastResponseStatus?.message
                                )
                            )
                        }
                    }
                }.awaitAll()
            }

            if (errors.isNotEmpty()) errorNotification.sendNotification(errors)
        }
        return Result.success()
    }

    private suspend fun checkAnService(request: RequestsEntity): HttpResponseStatusModel? =
        pingRepository.ping(request.httpRequestInfo)
}