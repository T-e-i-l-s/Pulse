package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R

/* Composable with a warning that applications cannot send notifications */
@Composable
fun NotificationsAreNotPermitted(openNotificationSettings: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.red).copy(0.2f))
            .clickable(onClick = openNotificationSettings)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.alert_circle),
            contentDescription = null,
            tint = colorResource(id = R.color.content),
            modifier = Modifier.size(26.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = stringResource(id = R.string.notification_permission_not_granted),
            style = MaterialTheme.typography.labelMedium,
            color = colorResource(id = R.color.content),
            modifier = Modifier.weight(1f)
        )
    }
}