package com.mustafin.ui_components.presentation.inputs

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mustafin.ui_components.R

/* Composable of material Text Field with custom UI */
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    shape: Shape = RoundedCornerShape(16.dp),
    maxLines: Int = Int.MAX_VALUE,
    isError: Boolean = false,
    readOnly: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.None,
    onKeyboardAction: () -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }

    TextField(
        maxLines = maxLines,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = colorResource(id = R.color.gray),
                style = MaterialTheme.typography.labelMedium
            )
        },
        isError = isError,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = colorResource(id = R.color.content),
            focusedTextColor = colorResource(id = R.color.content),
            errorTextColor = colorResource(id = R.color.content),
            cursorColor = colorResource(id = R.color.content),
            focusedContainerColor = colorResource(id = R.color.secondary_background),
            unfocusedContainerColor = colorResource(id = R.color.secondary_background),
            errorContainerColor = colorResource(id = R.color.secondary_background),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = colorResource(id = R.color.gray),
            focusedPlaceholderColor = colorResource(id = R.color.gray),
            errorPlaceholderColor = colorResource(id = R.color.gray),
            selectionColors = TextSelectionColors(
                handleColor = colorResource(id = R.color.content),
                backgroundColor = colorResource(id = R.color.blue).copy(0.4f)
            )
        ),
        modifier = modifier
            .onFocusChanged { isFocused = it.isFocused }
            .border(
                2.dp,
                when {
                    isError -> colorResource(id = R.color.red)
                    isFocused -> colorResource(id = R.color.content)
                    else -> Color.Transparent
                },
                shape = shape
            ),
        textStyle = MaterialTheme.typography.labelMedium,
        shape = shape,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onAny = { onKeyboardAction() }),
        readOnly = readOnly
    )
}