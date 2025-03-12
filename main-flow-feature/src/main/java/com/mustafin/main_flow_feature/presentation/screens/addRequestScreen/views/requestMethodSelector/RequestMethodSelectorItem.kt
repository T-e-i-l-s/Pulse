package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.requestMethodSelector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.utils.requests.RequestMethod

/* Composable that presents type of HTTP-request(GET, POST and etc.) */
@Composable
fun RequestMethodSelectorItem(requestMethod: RequestMethod) {
    Text(
        text = requestMethod.toString(),
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(requestMethod.colorRes).copy(0.05f))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        style = MaterialTheme.typography.labelMedium,
        color = colorResource(requestMethod.colorRes)
    )
}