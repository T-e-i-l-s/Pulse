package com.mustafin.ui_components.presentation.loaders

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mustafin.ui_components.R

/* An shimmer realization */
@Composable
fun SkeletonLoader(modifier: Modifier = Modifier) {
    val skeletonAnimationLabel = "SkeletonAnimation"
    val transition = rememberInfiniteTransition(label = skeletonAnimationLabel)
    val alpha by transition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = skeletonAnimationLabel
    )

    Box(
        modifier = modifier
            .background(color = colorResource(id = R.color.ternary_background).copy(alpha = alpha))
    )
}

@Composable
@Preview
private fun Preview() {
    SkeletonLoader(Modifier.size(200.dp, 100.dp))
}