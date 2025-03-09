package com.mustafin.pulse.ui.themes

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mustafin.pulse.R

val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.Black
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.ExtraBold
    ),
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.Bold
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.Bold
    ),
    labelLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.geologica)),
        fontWeight = FontWeight.Medium
    )
)