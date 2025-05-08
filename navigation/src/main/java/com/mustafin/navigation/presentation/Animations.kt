package com.mustafin.navigation.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

/* A collection of basic animations used in navigation */
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