package com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R

/* Header for the "Create New Request" screen */
@Composable
fun AddRequestScreenHeaderView(onBackButtonClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackButtonClick) {
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
}

@Composable
@Preview
private fun Preview() {
    AddRequestScreenHeaderView {}
}