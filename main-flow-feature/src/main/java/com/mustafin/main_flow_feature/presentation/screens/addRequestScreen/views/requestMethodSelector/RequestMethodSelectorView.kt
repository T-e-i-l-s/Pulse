package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.requestMethodSelector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mustafin.ping_feature.utils.http.HttpMethod

/* Composable for selecting an HTTP request method */
@Composable
fun RequestMethodSelectorView(
    selectedMethod: HttpMethod?,
    toggleIsSelected: (HttpMethod) -> Unit
) {
    val methods = listOf(
        HttpMethod.GET,
        HttpMethod.POST,
        HttpMethod.DELETE,
        HttpMethod.PUT,
        HttpMethod.PATCH
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(methods) { method ->
            RequestMethodSelectorItem(
                requestMethod = method,
                isSelected = method == selectedMethod,
                onClick = { toggleIsSelected(method) }
            )
        }
    }
}