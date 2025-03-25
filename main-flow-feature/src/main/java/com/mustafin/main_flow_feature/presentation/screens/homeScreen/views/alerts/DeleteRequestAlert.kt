package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.alerts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mustafin.main_flow_feature.R
import com.mustafin.ui_components.presentation.alerts.ConfirmationAlert

/* Composable with delete request confirmation alert implementation */
@Composable
fun DeleteRequestAlert(
    visible: Boolean,
    confirm: () -> Unit,
    dismiss: () -> Unit
) {
    if (visible) {
        ConfirmationAlert(
            title = stringResource(id = R.string.confirm_delete_request_dialog_title),
            text = stringResource(id = R.string.confirm_delete_request_dialog_text),
            confirmButtonText = stringResource(id = R.string.confirm_delete_request_dialog_confirm_button_text),
            denyButtonText = stringResource(id = R.string.confirm_delete_request_dialog_deny_button_text),
            onConfirm = confirm,
            onDismissRequest = dismiss
        )
    }
}