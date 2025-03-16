package com.mustafin.main_flow_feature.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafin.main_flow_feature.R
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.requestView.RequestView
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton
import org.koin.androidx.compose.koinViewModel

/* Home screen composable */
@Composable
fun HomeScreenView(
    navigateToAddRequestScreen: () -> Unit,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val requests = viewModel.requests.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
        }

        items(requests.value) {
            RequestView(request = it)
        }

        item {
            Spacer(
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(12.dp)
            )
        }
    }
}