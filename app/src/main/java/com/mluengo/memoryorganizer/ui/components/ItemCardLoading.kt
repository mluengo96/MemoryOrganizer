package com.mluengo.memoryorganizer.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.Shapes

@Composable
fun ItemCardLoading() {
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
        onClick = {  },
        enabled = false
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .height(IntrinsicSize.Max),
        ) {
            Column(
                modifier = Modifier.weight(1.75f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Column(
                    modifier = Modifier.weight(1f) // Adjust weight for first set of Texts
                ) {
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
                            .width(150.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray.copy(alpha = alpha))
                    )
                }
                Box(
                    modifier = Modifier
                        .height(spacing.spaceLarge)
                        .width(100.dp)
                        .clip(Shapes.small)
                        .background(Color.LightGray.copy(alpha = alpha))
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {

                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(Shapes.extraSmall)
                        .background(Color.LightGray.copy(alpha = alpha))
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray.copy(alpha = alpha))
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray.copy(alpha = alpha))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false, device = "id:pixel_7a")
@Composable
fun ItemCardLoadingPreview() {
    ItemCardLoading()
}