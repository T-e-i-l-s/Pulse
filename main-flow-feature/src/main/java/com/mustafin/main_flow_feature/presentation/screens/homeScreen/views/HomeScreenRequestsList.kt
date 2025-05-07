package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mustafin.core.utils.loading.LoadingState
import com.mustafin.core.utils.requests.RequestModel
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView.RequestView
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.skeletons.RequestViewSkeleton

/* List of requests created for home screen */
fun LazyListScope.homeScreenRequestsList(
    loadingState: LoadingState,
    requests: List<RequestModel>,
    deleteRequest: (Int) -> Unit,
    toggleRequestNotifications: (Int) -> Unit
) {
    if (requests.isEmpty()) {
        item {
            Text(
                text = stringResource(id = R.string.empty_requests_list),
                style = MaterialTheme.typography.labelMedium,
                color = colorResource(id = R.color.gray),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 36.dp)
            )
        }
    }

    when (loadingState) {
        LoadingState.LOADING -> {
            items(4) {
                RequestViewSkeleton()
            }
        }

        else -> {
            itemsIndexed(items = requests, key = { index, _ -> index }) { index, request ->
                RequestView(
                    request = request,
                    deleteRequest = { deleteRequest(index) },
                    toggleRequestNotifications = { toggleRequestNotifications(index) }
                )
            }
        }
    }
}