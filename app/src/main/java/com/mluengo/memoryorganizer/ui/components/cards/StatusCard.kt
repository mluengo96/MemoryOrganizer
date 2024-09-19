package com.mluengo.memoryorganizer.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun StatusCard(
    title: String,
) {
    val spacing = LocalSpacing.current
    Surface(
        modifier = Modifier.height(32.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(start = spacing.spaceMedium, end = spacing.spaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview(showBackground = false, device = "id:pixel_7a")
@Composable
fun StatusCardPreview() {
    StatusCard(
        title = "In Progress"
    )
}