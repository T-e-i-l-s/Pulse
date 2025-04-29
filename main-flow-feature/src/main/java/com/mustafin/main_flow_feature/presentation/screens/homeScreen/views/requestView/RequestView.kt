package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.http.HttpMethod
import com.mustafin.core.utils.http.HttpRequestModel
import com.mustafin.core.utils.http.HttpResponseStatusModel
import com.mustafin.main_flow_feature.R
import java.time.LocalDateTime

/* Composable of view with information about the request */
@Composable
fun RequestView(
    request: com.mustafin.core.utils.requests.RequestModel,
    deleteRequest: () -> Unit,
    toggleRequestNotifications: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp)
    ) {
        if (request.responseStatuses.isNotEmpty()) {
            ResponseStatusView(request.responseStatuses.last())
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = request.title,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content)
        )

        if (request.description.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = request.description,
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.content)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RequestMethodView(requestMethod = request.httpRequestInfo.httpMethod)

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = request.httpRequestInfo.url,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(onClick = toggleRequestNotifications, modifier = Modifier.size(24.dp)) {
                Icon(
                    painter = painterResource(id = if (request.notificationsEnabled) R.drawable.notifications else R.drawable.notifications_off),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray).copy(alpha = if (request.notificationsEnabled) 1f else 0.5f)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(onClick = deleteRequest, modifier = Modifier.size(24.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray)
                )
            }
        }

        if (request.responseStatuses.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))

            ResponseStatusesTimelineView(request.responseStatuses)
        }
    }
}

@Composable
@Preview
private fun Preview() {
    RequestView(
        request = com.mustafin.core.utils.requests.RequestModel(
            id = 1,
            title = "Schedule Api",
            description = "Api with my daily schedule",
            httpRequestInfo = HttpRequestModel(
                "https://test.test",
                HttpMethod.GET
            ),
            responseStatuses = listOf(
                HttpResponseStatusModel(
                    200,
                    "OK",
                    LocalDateTime.now()
                )
            )
        ),
        deleteRequest = {},
        toggleRequestNotifications = {}
    )
}