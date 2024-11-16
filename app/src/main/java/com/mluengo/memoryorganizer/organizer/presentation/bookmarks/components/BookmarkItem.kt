package com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.previewBookmark
import com.mluengo.memoryorganizer.organizer.presentation.models.BookmarkUi
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme
import com.mluengo.memoryorganizer.ui.theme.Shapes

@Composable
fun BookmarkItem(
    bookmarkUi: BookmarkUi,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    OutlinedCard(
        modifier = modifier
            .size(width = 200.dp, height = 250.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
        ) {
            Image(
                painter = painterResource(id = R.drawable.placholder),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.small)
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = bookmarkUi.title,
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = bookmarkUi.description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
private fun BookmarkItemPreview() {
    MemoryOrganizerTheme {
        BookmarkItem(
            bookmarkUi = previewBookmark,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

