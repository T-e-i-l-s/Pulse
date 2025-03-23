package com.mustafin.ui_components.presentation.inputs

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.mustafin.ui_components.R

/* Composable of material switch input with custom UI */
@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = colorResource(id = R.color.content),
            checkedThumbColor = colorResource(id = R.color.gray),
            uncheckedTrackColor = colorResource(id = R.color.ternary_background),
            uncheckedThumbColor = colorResource(id = R.color.gray),
        )
    )
}