package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.mustafin.main_flow_feature.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxScope.HomeScreenPullToRefreshIndicatorView(
    isRefreshing: Boolean,
    state: PullToRefreshState,
) {
    Indicator(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .statusBarsPadding(),
        isRefreshing = isRefreshing,
        containerColor = colorResource(id = R.color.ternary_background),
        color = colorResource(id = R.color.content),
        state = state
    )
}