package com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme

@Composable
fun BookmarkItemLoading(
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current

    // Creates an `InfiniteTransition` that runs infinite child animation values.
    val infiniteTransition = rememberInfiniteTransition(label = "infinite loading")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        // `infiniteRepeatable` repeats the specified duration-based `AnimationSpec` infinitely.
        animationSpec = infiniteRepeatable(
            // The `keyframes` animates the value by specifying multiple timestamps.
            animation = keyframes {
                // One iteration is 1000 milliseconds.
                durationMillis = 1000
                // 0.7f at the middle of an iteration.
                0.7f at 500
            },
            // When the value finishes animating from 0f to 1f, it repeats by reversing the
            // animation direction.
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    OutlinedCard(
        onClick = { },
        enabled = false,
        modifier = modifier
            .size(width = 200.dp, height = 250.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 200.dp, height = 125.dp)
                    .clip(RoundedCornerShape(spacing.spaceSmall))
                    .background(Color.LightGray.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Box(
                modifier = Modifier
                    .height(spacing.spaceMedium)
                    .width(250.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Box(
                modifier = Modifier
                    .height(spacing.spaceMedium)
                    .width(125.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Box(
                modifier = Modifier
                    .height(spacing.spaceMedium)
                    .width(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = alpha))
            )
        }
    }
}

@PreviewLightDark
@Composable
fun BookmarkItemLoadingPreview() {
    MemoryOrganizerTheme {
        BookmarkItemLoading()
    }
}