package com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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
            delay(1000L)
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
                bookmarkUi = data,
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
            .width(200.dp)
            //.size(width = 200.dp, height = 250.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.spaceSmall)
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(bookmarkUi.imageUrl)
                    .size(coil.size.Size(1280, 720))
                    .crossfade(true)
                    .build()
            )
            when (painter.state) {
                is AsyncImagePainter.State.Empty,
                is AsyncImagePainter.State.Loading -> {
                    LoadingImageState()
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                }
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = painter,
                        contentDescription = bookmarkUi.imageUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(Shapes.small)
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                }
                is AsyncImagePainter.State.Error -> {
                    // Show some error UI.
                }
            }
            Text(
                text = bookmarkUi.title,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)) {
                Text(
                    text = bookmarkUi.description,
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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

