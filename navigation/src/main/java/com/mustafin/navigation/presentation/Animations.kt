package com.mustafin.navigation.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object Animations {
    val slideIn = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(400)
    )

    val slideOut = slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(400)
    )
}