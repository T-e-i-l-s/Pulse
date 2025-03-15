package com.mustafin.ui_components.presentation.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mustafin.ui_components.R

/* Composable of button with custom UI */
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    backgroundColor: Color = colorResource(id = R.color.content),
    contentColor: Color = colorResource(id = R.color.secondary_background),
    isLoading: Boolean = false,
    icon: Painter? = null,
    enabled: Boolean = true
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor.copy(0.5f),
            disabledContentColor = contentColor.copy(0.5f)
        ),
        enabled = enabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = contentColor,
                trackColor = Color.Transparent,
                strokeWidth = 2.dp,
                strokeCap = StrokeCap.Round,
                modifier = Modifier.size(24.dp)
            )
        } else {
            icon?.let { iconSafe ->
                Icon(
                    painter = iconSafe,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )

                Spacer(modifier = Modifier.width(12.dp))
            }

            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}