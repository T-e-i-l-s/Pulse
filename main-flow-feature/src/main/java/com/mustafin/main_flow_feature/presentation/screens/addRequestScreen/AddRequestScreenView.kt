package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.requestMethodSelector.RequestMethodSelectorView
import com.mustafin.ui_components.presentation.buttons.CustomButton
import com.mustafin.ui_components.presentation.inputs.CustomTextField
import com.mustafin.ui_components.presentation.inputs.FullWidthTextField
import org.koin.androidx.compose.koinViewModel

/* Composable of create new request screen */
@Composable
fun AddRequestScreenView(
    navigateToHomeScreen: () -> Unit,
    popBackNavigationStack: () -> Unit,
    viewModel: AddRequestScreenViewModel = koinViewModel()
) {
    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()
    val isCreationEnabled = viewModel.isCreationEnabled.collectAsStateWithLifecycle()
    val selectedRequestMethod = viewModel.selectedRequestMethod.collectAsStateWithLifecycle()
    val requestUrl = viewModel.requestUrl.collectAsStateWithLifecycle()
    val title = viewModel.title.collectAsStateWithLifecycle()
    val description = viewModel.description.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .statusBarsPadding()
                .padding(bottom = 100.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                Modifier.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = popBackNavigationStack) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = colorResource(id = R.color.content)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stringResource(id = R.string.add_request_screen_title),
                    color = colorResource(id = R.color.content),
                    style = MaterialTheme.typography.displayLarge
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

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
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
            )

            FullWidthTextField(
                value = title.value,
                onValueChange = viewModel::setTitle,
                placeholder = stringResource(id = R.string.title_of_request_input_placeholder),
                singleLine = true,
                textStyle = MaterialTheme.typography.titleLarge,
                readOnly = isLoading.value,
                modifier = Modifier.fillMaxWidth()
            )

            FullWidthTextField(
                value = description.value,
                onValueChange = viewModel::setDescription,
                placeholder = stringResource(id = R.string.description_of_request_input_placeholder),
                readOnly = isLoading.value,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(12.dp)
        ) {
            CustomButton(
                text = stringResource(id = R.string.add_request_button_text),
                onClick = { viewModel.createRequest(navigateToHomeScreen) },
                enabled = isCreationEnabled.value,
                modifier = Modifier.fillMaxWidth(),
                isLoading = isLoading.value
            )
        }
    }
}