package com.mluengo.memoryorganizer.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import com.mluengo.memoryorganizer.ui.theme.onSurfaceVariantLight

@Composable
fun FolderCard(
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(spacing.spaceMedium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Outlined.Folder, contentDescription = "Folder Card")

                SuggestionChip(
                    enabled = false,
                    onClick = { Log.d("Suggestion chip", "hello world") },
                    label = { Text("In Progress") }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Note taking app", style = MemoryOrganizerTypography.headlineMedium, overflow = TextOverflow.Ellipsis, maxLines = 1)
                    Text(text = "10 items saved", style = MemoryOrganizerTypography.labelLarge, color = onSurfaceVariantLight, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis, maxLines = 1)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Rounded.MoreVert, contentDescription = "Localized description")
                }
            }
        }
    }
}

@Preview(showBackground = false, device = "id:pixel_7a")
@Composable
fun FolderCardPreview() {
    FolderCard(
        onClick = { }
    )
}