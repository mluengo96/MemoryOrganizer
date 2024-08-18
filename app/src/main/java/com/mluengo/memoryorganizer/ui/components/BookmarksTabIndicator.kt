package com.mluengo.memoryorganizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.primaryLight

@Composable
fun BookmarksTabIndicator(
    modifier: Modifier = Modifier,
    color: Color = primaryLight
) {
    val spacing = LocalSpacing.current
    Spacer(
        modifier
            .padding(horizontal = spacing.spaceExtraLarge)
            .height(4.dp)
            .background(color, RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
    )
}