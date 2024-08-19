package com.mluengo.memoryorganizer.ui.components.empty

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun EmptyArchivesTab(

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
                text = stringResource(id = R.string.archives_empty_text),
                textAlign = TextAlign.Center,
                style = MemoryOrganizerTypography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun EmptyArchivesTabPreview() {
    EmptyArchivesTab(

    )
}