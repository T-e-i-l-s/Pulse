package com.mustafin.main_flow_feature.data.repositories.requestsRepository

import com.mustafin.main_flow_feature.data.source.local.requestsSource.RequestsDao
import com.mustafin.main_flow_feature.utils.requests.RequestMethod
import com.mustafin.main_flow_feature.utils.requests.RequestModel
import com.mustafin.main_flow_feature.utils.requests.ResponseStatusModel
import kotlinx.coroutines.delay
import java.time.LocalDateTime

/*
Repository that provides requests created by user
!!! Mocked DATA
*/
class RequestsRepositoryImpl(
    private val requestsDao: RequestsDao
) : RequestsRepository {

    private val mockedRequestsList = listOf(
        RequestModel(
            id = 1,
            title = "Schedule Api",
            description = "Api with my daily schedule",
            url = "https://myshedule.org/api/v1",
            requestMethod = RequestMethod.GET,
            lastResponseStatus = ResponseStatusModel(
                200,
                "OK",
                LocalDateTime.now()
            )
        ),
        RequestModel(
            id = 2,
            title = "Reader App Web Site",
            description = "Landing of my startup",
            url = "https://reader-app.ru",
            requestMethod = RequestMethod.POST,
            lastResponseStatus = ResponseStatusModel(
                418,
                "I'm a teapot",
                LocalDateTime.now()
            )
        ),
        RequestModel(
            id = 3,
            title = "My web site",
            description = "Web site with information about me",
            url = "https://mustafin.online/",
            requestMethod = RequestMethod.DELETE,
            lastResponseStatus = ResponseStatusModel(
                500,
                "Internal Server Error",
                LocalDateTime.now()
            )
        ),
        RequestModel(
            id = 4,
            title = "Example API",
            description = "API that provides information about products",
            url = "https://exampleapi.com/products",
            requestMethod = RequestMethod.PUT,
            lastResponseStatus = ResponseStatusModel(
                300,
                "Multiple Choices",
                LocalDateTime.now()
            )
        ),
        RequestModel(
            id = 5,
            title = "User Service",
            description = "Service for managing user accounts",
            url = "https://userservice.com/account",
            requestMethod = RequestMethod.PATCH,
            lastResponseStatus = ResponseStatusModel(
                200,
                "OK",
                LocalDateTime.now()
            )
        )
    )

    override suspend fun getAllRequests(): List<RequestModel> {
        delay(600)
        return mockedRequestsList
    }

    override suspend fun addRequest(request: RequestModel) {

    }

    override suspend fun deleteRequest(requestId: Int) {
        TODO("Not yet implemented")
    }
}