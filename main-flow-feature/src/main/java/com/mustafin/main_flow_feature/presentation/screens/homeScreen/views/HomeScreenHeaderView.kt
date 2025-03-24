package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R
import com.mustafin.ui_components.presentation.buttons.CustomTinyButton

/* View of the upper block of the main screen */
@Composable
fun HomeScreenHeaderView(onAddButtonClick: () -> Unit) {
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
            onClick = onAddButtonClick,
            icon = painterResource(id = R.drawable.plus)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    HomeScreenHeaderView {}
}