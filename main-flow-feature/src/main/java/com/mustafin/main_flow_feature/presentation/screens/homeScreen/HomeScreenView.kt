package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import android.Manifest
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.NotificationsAreNotPermitted
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView.RequestView
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

/* Home screen composable */
@Composable
fun HomeScreenView(
    navigateToAddRequestScreen: () -> Unit,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val requests = viewModel.requests.collectAsStateWithLifecycle()
    val notificationPermissionWasGranted =
        viewModel.notificationPermissionWasGranted.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val notificationPermissionRequestLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
        viewModel::onPermissionRequestResult
    )

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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .height(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.all_requests_title),
                    style = MaterialTheme.typography.displayLarge,
                    color = colorResource(id = R.color.content),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                CustomTinyButton(
                    text = stringResource(id = R.string.new_request_button_text),
                    onClick = navigateToAddRequestScreen,
                    icon = painterResource(id = R.drawable.plus)
                )
            }

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

        items(requests.value, key = { it.id }) { request ->
            val animationDuration = 300

            var visible by remember { mutableStateOf(true) }

            LaunchedEffect(visible) {
                if (!visible) {
                    delay(animationDuration.toLong())
                    viewModel.deleteRequest(request)
                }
            }

            AnimatedVisibility(
                visible = visible,
                exit = fadeOut(tween(animationDuration)) + shrinkVertically(tween(animationDuration))
            ) {
                Column {
                    RequestView(
                        request = request,
                        deleteRequest = { visible = false }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}