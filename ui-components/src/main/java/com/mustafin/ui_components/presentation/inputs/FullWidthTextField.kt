package com.mustafin.ui_components.presentation.inputs

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.mustafin.ui_components.R

/* Composable of material Text Field with custom UI */
@Composable
fun FullWidthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = false,
    isError: Boolean = false,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        maxLines = maxLines,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = colorResource(id = R.color.gray),
                style = textStyle
            )
        },
        isError = isError,
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = colorResource(id = R.color.content),
            focusedTextColor = colorResource(id = R.color.content),
            errorTextColor = colorResource(id = R.color.content),
            cursorColor = colorResource(id = R.color.content),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = colorResource(id = R.color.gray),
            focusedPlaceholderColor = colorResource(id = R.color.gray),
            errorPlaceholderColor = colorResource(id = R.color.gray)
        ),
        modifier = modifier,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        ),
        readOnly = readOnly
    )
}