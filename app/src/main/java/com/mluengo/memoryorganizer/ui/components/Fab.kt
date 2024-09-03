package com.mluengo.memoryorganizer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun Fab(
    extended: Boolean,
    resourceId: Int,
    onFabClick: () -> Unit,
    modifier: Modifier
) {
    val spacing = LocalSpacing.current
    FloatingActionButton(
        onClick = onFabClick,
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = stringResource(id = resourceId))

            // Toggle the visibility of the content with animation.
            AnimatedVisibility(visible = extended) {
                Text(
                    text = stringResource(id = resourceId),
                    modifier = Modifier
                        .padding(start = spacing.spaceSmall),
                    style = MemoryOrganizerTypography.bodyMedium
                )
            }
        }
    }
}