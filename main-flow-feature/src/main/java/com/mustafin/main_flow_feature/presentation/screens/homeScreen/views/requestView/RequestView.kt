package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.utils.requests.RequestMethod
import com.mustafin.main_flow_feature.utils.requests.RequestModel
import com.mustafin.main_flow_feature.utils.requests.ResponseStatusModel
import java.time.LocalDateTime

/* Composable of view with information about the request */
@Composable
fun RequestView(request: RequestModel) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp)
    ) {
        request.lastResponseStatus?.let { responseStatusSafe ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ResponseStatusView(responseStatusSafe)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = request.name,
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.content)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = request.description,
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.content)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            RequestMethodView(requestMethod = request.requestMethod)

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = request.url,
                style = MaterialTheme.typography.labelSmall,
                color = colorResource(id = R.color.gray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    RequestView(
        request = RequestModel(
            id = 1,
            name = "Schedule Api",
            description = "Api with my daily schedule",
            url = "https://myshedule.org/api/v1",
            requestMethod = RequestMethod.GET,
            lastResponseStatus = ResponseStatusModel(
                200,
                "OK",
                LocalDateTime.now()
            )
        )
    )
}