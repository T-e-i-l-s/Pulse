package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.utils.requests.RequestMethod

/* Composable that contains small text with request type */
@Composable
fun RequestMethodView(requestMethod: RequestMethod) {
    val contentColor = colorResource(id = requestMethod.colorRes)

    Text(
        text = requestMethod.toString(),
        style = MaterialTheme.typography.labelSmall,
        color = contentColor,
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .background(contentColor.copy(0.1f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
@Preview
private fun Preview() {
    RequestMethodView(RequestMethod.GET)
}