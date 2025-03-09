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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.utils.requests.ResponseStatusModel
import java.time.LocalDateTime

/* View with short information about server response status*/
@Composable
fun ResponseStatusView(responseStatus: ResponseStatusModel) {
    val contentColor = if (responseStatus.statusCode >= 500) {
        colorResource(id = R.color.yellow)
    } else if (responseStatus.statusCode >= 400) {
        colorResource(id = R.color.red)
    } else if (responseStatus.statusCode >= 300) {
        colorResource(id = R.color.gray)
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
            text = "${responseStatus.statusCode} • ${responseStatus.message}",
            style = MaterialTheme.typography.titleSmall,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ResponseStatusView(
        responseStatus = ResponseStatusModel(
            200,
            "OK",
            LocalDateTime.now()
        )
    )
}