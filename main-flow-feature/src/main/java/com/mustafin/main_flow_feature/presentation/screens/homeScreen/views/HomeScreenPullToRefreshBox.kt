package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.mustafin.main_flow_feature.R

/* Composable function for the pull-to-refresh wrapper on the home screen */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable BoxScope.() -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            Indicator(
                state = pullToRefreshState,
                isRefreshing = isRefreshing,
                containerColor = colorResource(id = R.color.ternary_background),
                color = colorResource(id = R.color.content),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        content = content
    )
}