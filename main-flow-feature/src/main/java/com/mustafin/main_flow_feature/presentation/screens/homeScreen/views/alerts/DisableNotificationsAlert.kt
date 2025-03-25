package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.alerts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mustafin.main_flow_feature.R
import com.mustafin.ui_components.presentation.alerts.ConfirmationAlert

/* Composable with disable notifications confirmation alert implementation */
@Composable
fun DisableNotificationsAlert(
    visible: Boolean,
    confirm: () -> Unit,
    dismiss: () -> Unit
) {
    if (visible) {
        ConfirmationAlert(
            title = stringResource(id = R.string.confirm_disable_notifications_request_dialog_title),
            text = stringResource(id = R.string.confirm_disable_notifications_request_dialog_text),
            confirmButtonText = stringResource(id = R.string.confirm_disable_notifications_request_dialog_confirm_button_text),
            denyButtonText = stringResource(id = R.string.confirm_disable_notifications_request_dialog_deny_button_text),
            onConfirm = confirm,
            onDismissRequest = dismiss
        )
    }
}