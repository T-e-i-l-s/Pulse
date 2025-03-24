package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.HomeScreenHeaderView
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.HomeScreenPullToRefreshIndicatorView
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.NotificationsAreNotPermitted
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView.RequestView
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.skeletons.RequestViewSkeleton
import com.mustafin.main_flow_feature.utils.loading.LoadingState
import com.mustafin.ui_components.presentation.alerts.ConfirmationAlert
import com.mustafin.ui_components.presentation.vibration.CustomVibrationManager
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

/* Home screen composable */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    navigateToAddRequestScreen: () -> Unit,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val vibrationManager: CustomVibrationManager = koinInject()

    val loadingState = viewModel.loadingState.collectAsStateWithLifecycle()
    val requests = viewModel.requests.collectAsStateWithLifecycle()
    val notificationPermissionWasGranted =
        viewModel.notificationPermissionWasGranted.collectAsStateWithLifecycle()
    val showConfirmDisableNotificationsDialog =
        viewModel.showConfirmDisableNotificationsDialog.collectAsStateWithLifecycle()
    val showConfirmDeleteRequestDialog =
        viewModel.showConfirmDeleteRequestDialog.collectAsStateWithLifecycle()

    // Permission request window launcher
    val notificationPermissionRequestLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
        viewModel::onPermissionRequestResult
    )

    // App notifications settings launcher
    val notificationSettingsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            viewModel.checkNotificationPermissionsStatus()
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Getting POST_NOTIFICATIONS permission if needed
            viewModel.checkNotificationPermissionsStatus()
            notificationPermissionRequestLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    // Pull to refresh variables
    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing = loadingState.value in listOf(LoadingState.LOADING, LoadingState.UPDATING)

    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            vibrationManager.shortDoubleVibration()
            viewModel.updateData()
        },
        indicator = {
            HomeScreenPullToRefreshIndicatorView(
                isRefreshing = isRefreshing,
                state = pullToRefreshState
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Spacer(
                    modifier = Modifier
                        .statusBarsPadding()
                        .height(12.dp)
                )

                HomeScreenHeaderView(navigateToAddRequestScreen)

                notificationPermissionWasGranted.value?.let { notificationPermissionWasGrantedSafe ->
                    AnimatedVisibility(
                        visible = !notificationPermissionWasGrantedSafe,
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))

                        NotificationsAreNotPermitted {
                            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                            notificationSettingsLauncher.launch(intent)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (requests.value.isEmpty()) {
                    Spacer(modifier = Modifier.height(36.dp))

                    Text(
                        text = stringResource(id = R.string.empty_requests_list),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorResource(id = R.color.gray),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    )
                }
            }


            when (loadingState.value) {
                LoadingState.LOADING -> {
                    items(4) {
                        RequestViewSkeleton()
                    }
                }

                else -> {
                    itemsIndexed(
                        requests.value,
                        key = { _, request -> request.id }) { index, request ->
                        RequestView(
                            request = request,
                            deleteRequest = { viewModel.deleteRequest(request) },
                            toggleRequestNotifications = {
                                viewModel.toggleNotificationsRequest(index)
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }

    if (showConfirmDisableNotificationsDialog.value) {
        ConfirmationAlert(
            title = stringResource(id = R.string.confirm_disable_notifications_request_dialog_title),
            text = stringResource(id = R.string.confirm_disable_notifications_request_dialog_text),
            confirmButtonText = stringResource(id = R.string.confirm_disable_notifications_request_dialog_confirm_button_text),
            denyButtonText = stringResource(id = R.string.confirm_disable_notifications_request_dialog_deny_button_text),
            onConfirm = viewModel::confirmToggleNotificationsRequest,
            onDismissRequest = viewModel::denyConfirmToggleNotificationsRequest
        )
    }

    if (showConfirmDeleteRequestDialog.value) {
        ConfirmationAlert(
            title = stringResource(id = R.string.confirm_delete_request_dialog_title),
            text = stringResource(id = R.string.confirm_delete_request_dialog_text),
            confirmButtonText = stringResource(id = R.string.confirm_delete_request_dialog_confirm_button_text),
            denyButtonText = stringResource(id = R.string.confirm_delete_request_dialog_deny_button_text),
            onConfirm = viewModel::confirmDeleteRequest,
            onDismissRequest = viewModel::denyDeleteRequest
        )
    }
}