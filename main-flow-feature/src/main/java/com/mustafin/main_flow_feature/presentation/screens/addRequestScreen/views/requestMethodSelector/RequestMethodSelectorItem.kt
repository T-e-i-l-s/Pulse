package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.requestMethodSelector

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.http.HttpMethod
import com.mustafin.main_flow_feature.R

/* Composable for displaying the HTTP request method (e.g., GET, POST, etc.) */
@Composable
fun RequestMethodSelectorItem(
    requestMethod: HttpMethod,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(requestMethod.colorRes).copy(if (isSelected) 0.3f else 0.1f))
            .clickable(onClick = onClick)
            .animateContentSize()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        val contentColor = colorResource(requestMethod.colorRes).copy(if (isSelected) 1f else 0.5f)

        Text(
            text = requestMethod.toString(),
            style = MaterialTheme.typography.labelMedium,
            color = contentColor
        )

        if (isSelected) {
            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                painter = painterResource(id = R.drawable.x),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = contentColor
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    Row {
        RequestMethodSelectorItem(
            requestMethod = HttpMethod.GET,
            isSelected = true,
            onClick = {}
        )

        Spacer(modifier = Modifier.width(12.dp))

        RequestMethodSelectorItem(
            requestMethod = HttpMethod.GET,
            isSelected = false,
            onClick = {}
        )
    }
}