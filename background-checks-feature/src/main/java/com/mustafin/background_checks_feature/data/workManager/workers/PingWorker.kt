package com.mustafin.background_checks_feature.data.workManager.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mustafin.local_data_source.data.local.requestsSource.RequestsDao
import com.mustafin.local_data_source.data.local.requestsSource.RequestsEntity
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

            coroutineScope {
                requests.map { request ->
                    async {
                        val updatedRequestEntity = checkAnService(request)

                        val statusCode = updatedRequestEntity.lastResponseStatus?.statusCode
                        if (
                            (statusCode == null || statusCode >= 400) &&
                            updatedRequestEntity.notificationsEnabled
                        ) {
                            errors.add(
                                ErrorNotificationModel(
                                    updatedRequestEntity.httpRequestInfo.url,
                                    updatedRequestEntity.httpRequestInfo.httpMethod.toString(),
                                    updatedRequestEntity.lastResponseStatus?.statusCode,
                                    updatedRequestEntity.lastResponseStatus?.message
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

    private suspend fun checkAnService(request: RequestsEntity): RequestsEntity {
        // Executing request
        val newResponseStatus = pingRepository.ping(request.httpRequestInfo)
        val updatedRequest = request.copy(lastResponseStatus = newResponseStatus)

        // Updating values in cache
        requestsDao.insertRequest(updatedRequest)

        return updatedRequest
    }
}