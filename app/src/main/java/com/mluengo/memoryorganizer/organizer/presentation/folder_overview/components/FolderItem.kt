package com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components.StatusCard
import com.mluengo.memoryorganizer.organizer.presentation.models.FolderUi
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme

@Composable
fun FolderItem(
    folderUi: FolderUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        modifier = modifier
            .size(150.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
        ) {
            Text(
                text = folderUi.title,
                style = MaterialTheme.typography.labelLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            StatusCard(
                title = "2",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun FolderItemPreview() {
    MemoryOrganizerTheme {
        FolderItem(
            folderUi = FolderUi(
                id = "",
                title = "Android development",
                status = "In Progress",
                description = "Something something",
                itemList = listOf(
                    "",
                    "",
                    ""
                )
            ),
            onClick = { },
        )
    }
}