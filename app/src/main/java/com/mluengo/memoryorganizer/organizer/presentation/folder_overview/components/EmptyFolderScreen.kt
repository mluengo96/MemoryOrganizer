package com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun EmptyFolderScreen(

) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(spacing.spaceMedium),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.welcome_title),
                textAlign = TextAlign.Center,
                style = MemoryOrganizerTypography.displayLarge,
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = stringResource(id = R.string.welcome_text),
                textAlign = TextAlign.Center,
                style = MemoryOrganizerTypography.bodyLarge,
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(spacing.spaceExtraLarge),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .rotate(27f)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun EmptyFolderScreenPreview() {
    EmptyFolderScreen(
    )
}