package com.mustafin.main_flow_feature.presentation.screens.homeScreen.views.skeletons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mustafin.main_flow_feature.R
import com.mustafin.ui_components.presentation.loaders.SkeletonLoader

@Composable
fun RequestViewSkeleton() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkeletonLoader(
                Modifier
                    .size(height = 20.dp, width = 200.dp)
                    .clip(CircleShape)
            )
            SkeletonLoader(
                Modifier
                    .size(height = 20.dp, width = 100.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        SkeletonLoader(
            Modifier
                .size(height = 28.dp, width = 230.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            SkeletonLoader(
                Modifier
                    .height(20.dp)
                    .weight(1f)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            SkeletonLoader(
                Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(12.dp))

            SkeletonLoader(
                Modifier
                    .size(24.dp)
                    .clip(CircleShape)
            )
        }
    }
}