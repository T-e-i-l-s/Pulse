package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.core.utils.loading.LoadingState
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.HomeScreenHeaderView
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.HomeScreenPullToRefreshBox
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.NotificationsAreNotPermitted
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.alerts.DeleteRequestAlert
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.alerts.DisableNotificationsAlert
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.homeScreenRequestsList
import com.mustafin.ui_components.presentation.vibration.CustomVibrationManager
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

/* Home screen composable */
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
            viewModel.checkNotificationPermissionsStatus()
            notificationPermissionRequestLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    HomeScreenPullToRefreshBox(
        isRefreshing = loadingState.value in listOf(
            com.mustafin.core.utils.loading.LoadingState.LOADING,
            com.mustafin.core.utils.loading.LoadingState.UPDATING
        ),
        onRefresh = {
            vibrationManager.shortDoubleVibration()
            viewModel.updateData()
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(1f)
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .statusBarsPadding()
                        .height(12.dp)
                )

                HomeScreenHeaderView(navigateToAddRequestScreen)

                notificationPermissionWasGranted.value?.let { notificationPermissionWasGrantedSafe ->
                    NotificationsAreNotPermitted(
                        visible = notificationPermissionWasGrantedSafe,
                        openNotificationSettings = {
                            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                                .putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                            notificationSettingsLauncher.launch(intent)
                        }
                    )
                }
            }

            homeScreenRequestsList(
                loadingState = loadingState.value,
                requests = requests.value,
                deleteRequest = viewModel::deleteRequest,
                toggleRequestNotifications = viewModel::toggleNotifications
            )

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }

    DisableNotificationsAlert(
        visible = showConfirmDisableNotificationsDialog.value,
        confirm = viewModel::confirmToggleNotificationsRequest,
        dismiss = viewModel::denyConfirmToggleNotificationsRequest
    )

    DeleteRequestAlert(
        visible = showConfirmDeleteRequestDialog.value,
        confirm = viewModel::confirmDeleteRequest,
        dismiss = viewModel::denyDeleteRequest
    )
}