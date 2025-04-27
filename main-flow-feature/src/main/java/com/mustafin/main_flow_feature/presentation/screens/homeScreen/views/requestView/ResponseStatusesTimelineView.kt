package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
import java.time.LocalDateTime

/* The timeline view that shows a list of the last 50 response statuses */
@Composable
fun ResponseStatusesTimelineView(responseStatusesList: List<HttpResponseStatusModel?>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        val timelineStepsCount = 50

        for (i in 1..timelineStepsCount) {
            val color = if (i > timelineStepsCount - responseStatusesList.size) {
                val sectionIndex = i - (timelineStepsCount - responseStatusesList.size) - 1
                val responseStatus = responseStatusesList[sectionIndex]
                when (responseStatus?.statusCode) {
                    null, in 400..499 -> colorResource(R.color.red)
                    in 500..599 -> colorResource(R.color.yellow)
                    in 300..399 -> colorResource(R.color.blue)
                    else -> colorResource(R.color.green)
                }
            } else {
                colorResource(R.color.ternary_background)
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.7f))
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    ResponseStatusesTimelineView(
        responseStatusesList = listOf(
            HttpResponseStatusModel(
                200,
                "OK",
                LocalDateTime.now()
            ),
            null,
            HttpResponseStatusModel(
                400,
                "Client error",
                LocalDateTime.now()
            ), HttpResponseStatusModel(
                500,
                "Server Error",
                LocalDateTime.now()
            )
        )
    )
}