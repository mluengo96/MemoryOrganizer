package com.mluengo.memoryorganizer.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun HeaderFolders() {
    val spacing = LocalSpacing.current
    Spacer(modifier = Modifier.height(spacing.spaceLarge))
    Text(
        text = stringResource(id = R.string.folders_title),
        textAlign = TextAlign.Start,
        style = MemoryOrganizerTypography.displayLarge,
    )
    Spacer(modifier = Modifier.height(spacing.spaceLarge))
}