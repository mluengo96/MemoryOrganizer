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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.core.presentation.components.BookmarkItemInvalid
import com.mluengo.memoryorganizer.organizer.domain.model.LinkViewState
import com.mluengo.memoryorganizer.organizer.domain.model.fetchMetadata
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.previewBookmark
import com.mluengo.memoryorganizer.organizer.presentation.models.BookmarkUi
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme
import com.mluengo.memoryorganizer.ui.theme.Shapes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BookmarkItem(
    bookmarkUi: BookmarkUi,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val scope = rememberCoroutineScope()
    var loadingState by remember {
        mutableStateOf<LinkViewState>(LinkViewState.Loading)
    }

    LaunchedEffect(bookmarkUi.url) {
        scope.launch(Dispatchers.IO) {
            delay(10000L)
            loadingState = fetchMetadata(bookmarkUi.url)
        }
    }

    when (val state = loadingState) {
        is LinkViewState.Failure -> BookmarkItemInvalid()
        LinkViewState.Loading -> BookmarkItemLoading()
        is LinkViewState.Success -> {
            val metadata = state.metadata
            val data = BookmarkUi(
                title = metadata.title!!,
                description = metadata.description!!,
                imageUrl = metadata.imageUrl!!,
                url = metadata.url
            )
            SuccessfulItem(
                bookmarkUi = bookmarkUi,
                modifier = modifier
            )
        }
    }
}

@Composable
fun SuccessfulItem(
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

@PreviewLightDark
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

