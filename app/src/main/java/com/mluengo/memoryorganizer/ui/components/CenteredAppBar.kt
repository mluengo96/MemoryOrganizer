package com.mluengo.memoryorganizer.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAppBar(
    title: String,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MemoryOrganizerTypography.titleLarge
            )
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun CenterAppBarPreview() {
    CenterAppBar(
        title = "My items"
    )
}