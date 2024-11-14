package com.mluengo.memoryorganizer.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme

@Composable
fun BackToTopButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = onClick, modifier = modifier) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowUp,
                contentDescription = stringResource(R.string.back_to_top)
            )
            Text(
                text = stringResource(R.string.back_to_top),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BackToTopButtonPreview() {
    MemoryOrganizerTheme {
        BackToTopButton()
    }
}