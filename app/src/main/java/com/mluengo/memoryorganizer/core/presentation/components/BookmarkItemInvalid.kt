package com.mluengo.memoryorganizer.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LinkOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme

@Composable
fun BookmarkItemInvalid(
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        onClick = { },
        enabled = false,
        modifier = modifier
            .size(width = 200.dp, height = 250.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .fillMaxSize(),
        ) {
            Icon(
                imageVector = Icons.Rounded.LinkOff,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Red
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = "An error occurred while trying to load the information",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun BookmarkItemInvalidPreview(

) {
    MemoryOrganizerTheme {
        BookmarkItemInvalid()
    }
}