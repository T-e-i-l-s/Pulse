package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.utils.time.toSimpleTimeString
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
import java.time.LocalDateTime

/* View with short information about server response status */
@Composable
fun ResponseStatusView(responseStatus: HttpResponseStatusModel?) {
    responseStatus?.statusCode?.let { responseStatusCodeSafe ->
        val contentColor = when (responseStatusCodeSafe) {
            in 400..499 -> colorResource(R.color.red)
            in 500..599 -> colorResource(R.color.yellow)
            in 300..399 -> colorResource(R.color.blue)
            else -> colorResource(R.color.green)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.updated_at) + " " +
                        responseStatus.updatedAt.toSimpleTimeString(),
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.gray),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(contentColor.copy(0.1f))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$responseStatusCodeSafe",
                    style = MaterialTheme.typography.titleSmall,
                    color = contentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (!responseStatus.message.isNullOrBlank()) {
                    Text(
                        text = " • ${responseStatus.message}",
                        style = MaterialTheme.typography.titleSmall,
                        color = contentColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    } ?: Text(
        text = stringResource(id = R.string.service_unavailable),
        style = MaterialTheme.typography.labelMedium,
        color = colorResource(id = R.color.gray)
    )
}

@Composable
@Preview
private fun Preview() {
    ResponseStatusView(
        responseStatus = HttpResponseStatusModel(
            200,
            "OK",
            LocalDateTime.now()
        )
    )
}