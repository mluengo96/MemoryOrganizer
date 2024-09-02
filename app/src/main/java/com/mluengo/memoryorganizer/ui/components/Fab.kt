package com.mluengo.memoryorganizer.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun Fab(
    text: String,
    onFabClick: () -> Unit,
    modifier: Modifier
) {
    val spacing = LocalSpacing.current
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(spacing.spaceMedium)) {
        Row(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                onClick = onFabClick,
                modifier = modifier,
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = text)
            }
        }
    }
}