package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.AddRequestScreenViewModel
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.requestMethodSelector.RequestMethodSelectorView
import com.mustafin.ui_components.presentation.inputs.CustomSwitch
import com.mustafin.ui_components.presentation.inputs.CustomTextField
import com.mustafin.ui_components.presentation.inputs.FullWidthTextField

/* Composable for the request form, including text fields, selectors, and other input elements */
@Composable
fun ColumnScope.RequestConstructorView(viewModel: AddRequestScreenViewModel) {
    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()
    val selectedRequestMethod = viewModel.selectedRequestMethod.collectAsStateWithLifecycle()
    val requestUrl = viewModel.requestUrl.collectAsStateWithLifecycle()
    val title = viewModel.title.collectAsStateWithLifecycle()
    val description = viewModel.description.collectAsStateWithLifecycle()
    val notificationsEnabled = viewModel.notificationsEnabled.collectAsStateWithLifecycle()
    val showRequestUrlValidationError =
        viewModel.showRequestUrlValidationError.collectAsStateWithLifecycle()

    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester = remember { FocusRequester() }

    RequestMethodSelectorView(
        selectedMethod = selectedRequestMethod.value,
        toggleIsSelected = viewModel::selectRequestMethod
    )

    Spacer(modifier = Modifier.height(12.dp))

    CustomTextField(
        value = requestUrl.value,
        onValueChange = viewModel::setRequestUrl,
        placeholder = stringResource(id = R.string.url_text_field_placeholder),
        readOnly = isLoading.value,
        keyboardType = KeyboardType.Uri,
        maxLines = 1,
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        imeAction = ImeAction.Next,
        onKeyboardAction = titleFocusRequester::requestFocus
    )

    AnimatedVisibility(visible = showRequestUrlValidationError.value) {
        Column {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.request_url_validation_error_text),
                color = colorResource(id = R.color.red),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }

    FullWidthTextField(
        value = title.value,
        onValueChange = viewModel::setTitle,
        placeholder = stringResource(id = R.string.title_of_request_input_placeholder),
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge,
        readOnly = isLoading.value,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(titleFocusRequester),
        imeAction = ImeAction.Next,
        onKeyboardAction = descriptionFocusRequester::requestFocus
    )

    FullWidthTextField(
        value = description.value,
        onValueChange = viewModel::setDescription,
        placeholder = stringResource(id = R.string.description_of_request_input_placeholder),
        readOnly = isLoading.value,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .focusRequester(descriptionFocusRequester)
    )

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomSwitch(
            checked = notificationsEnabled.value,
            onCheckedChange = viewModel::setNotificationsEnabled
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = stringResource(id = R.string.notifications_enabled_text),
            color = colorResource(id = R.color.content),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
        )
    }
}