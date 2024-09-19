package com.mluengo.memoryorganizer.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun FolderCard(
    onClick: () -> Unit,
    title: String
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(spacing.spaceMedium),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Icon(imageVector = Icons.Outlined.Folder, contentDescription = "Folder Card")

                if (true) {
                    StatusCard(
                        title = "In Progress"
                    )
                }
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = title, style = MaterialTheme.typography.headlineMedium, overflow = TextOverflow.Ellipsis, maxLines = 1)
                    Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                    Text(text = "10 items saved", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, overflow = TextOverflow.Ellipsis, maxLines = 1)
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
        onClick = { },
        title = "Note taking app",
    )
}