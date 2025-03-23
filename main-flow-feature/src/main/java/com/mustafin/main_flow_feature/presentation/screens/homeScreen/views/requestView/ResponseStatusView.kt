package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import com.mustafin.ping_feature.utils.http.HttpResponseStatusModel
import java.time.LocalDateTime

/* View with short information about server response status*/
@Composable
fun ResponseStatusView(responseStatus: HttpResponseStatusModel) {
    responseStatus.statusCode?.let { responseStatusCodeSafe ->
        val contentColor = if (responseStatusCodeSafe >= 500) {
            colorResource(id = R.color.yellow)
        } else if (responseStatusCodeSafe >= 400) {
            colorResource(id = R.color.red)
        } else if (responseStatusCodeSafe >= 300) {
            colorResource(id = R.color.blue)
        } else {
            colorResource(id = R.color.green)
        }

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
    } ?: Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(colorResource(id = R.color.ternary_background))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.request_was_not_completed),
            style = MaterialTheme.typography.titleSmall,
            color = colorResource(id = R.color.gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
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