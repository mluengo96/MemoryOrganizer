package com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import com.mluengo.memoryorganizer.ui.theme.Shapes
import com.mluengo.memoryorganizer.ui.theme.tertiaryContainerLight

@Composable
fun SourceCard() {
    val spacing = LocalSpacing.current
    Surface(
        modifier = Modifier.height(32.dp),
        color = tertiaryContainerLight,
        shape = Shapes.small,
    ) {
        Row(
            modifier = Modifier.padding(start = spacing.spaceSmall, end = spacing.spaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.x_twitter_brands_solid), contentDescription = "Twitter", modifier = Modifier.height(18.dp))
            Spacer(modifier = Modifier.width(spacing.spaceSmall))
            Text(
                text = "Twitter",
                style = MemoryOrganizerTypography.labelLarge
            )
        }
    }
}

@Preview(showBackground = false, device = "id:pixel_7a")
@Composable
fun SourceCardPreview() {
    SourceCard()
}