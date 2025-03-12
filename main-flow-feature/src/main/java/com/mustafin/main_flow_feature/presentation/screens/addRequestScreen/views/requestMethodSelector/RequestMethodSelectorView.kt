package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.requestMethodSelector

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.utils.requests.RequestMethod

/* Composable with request method selector */
@Composable
fun RequestMethodSelectorView() {
    val methods = listOf(
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.DELETE,
        RequestMethod.PUT,
        RequestMethod.PATCH
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(methods) {
            RequestMethodSelectorItem(requestMethod = it)
        }
    }
}