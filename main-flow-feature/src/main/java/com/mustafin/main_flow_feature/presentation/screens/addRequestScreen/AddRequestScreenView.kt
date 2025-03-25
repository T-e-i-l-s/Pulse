package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.AddRequestScreenHeaderView
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views.RequestConstructorView
import com.mustafin.ui_components.presentation.buttons.CustomButton
import org.koin.androidx.compose.koinViewModel

/* Composable for the "Create New Request" screen */
@Composable
fun AddRequestScreenView(
    navigateToHomeScreen: () -> Unit,
    popBackNavigationStack: () -> Unit,
    viewModel: AddRequestScreenViewModel = koinViewModel()
) {
    val isLoading = viewModel.isLoading.collectAsStateWithLifecycle()
    val isCreationEnabled = viewModel.isCreationEnabled.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .imePadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = focusManager::clearFocus
            )
    ) {
        Spacer(
            modifier = Modifier
                .statusBarsPadding()
                .height(12.dp)
        )

        AddRequestScreenHeaderView(onBackButtonClick = popBackNavigationStack)

        Spacer(modifier = Modifier.height(12.dp))

        RequestConstructorView(viewModel)

        Spacer(modifier = Modifier.height(12.dp))

        CustomButton(
            text = stringResource(id = R.string.add_request_button_text),
            onClick = { viewModel.createRequest(navigateToHomeScreen) },
            enabled = isCreationEnabled.value,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            isLoading = isLoading.value
        )

        Spacer(
            modifier = Modifier
                .navigationBarsPadding()
                .height(12.dp)
        )
    }
}