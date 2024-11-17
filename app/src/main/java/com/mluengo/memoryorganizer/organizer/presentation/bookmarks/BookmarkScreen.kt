package com.mluengo.memoryorganizer.organizer.presentation.bookmarks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.core.presentation.components.MoTopAppBar
import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components.BookmarkItem
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components.EmptyBookmarksTab
import com.mluengo.memoryorganizer.organizer.presentation.models.toBookmarkUi
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkScreen(
    viewModel: BookmarkListViewModel = koinViewModel(),
    lazyGridState: LazyGridState,
    isTopAppBarVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        // Ensure the list always starts at the top when entering this screen
        lazyGridState.scrollToItem(0)
    }

    if (state.bookmarks.isEmpty()) {
        EmptyBookmarksTab()
    } else {
        Column {
            MoTopAppBar(
                title = stringResource(id = R.string.bookmarks_title),
                isVisible = isTopAppBarVisible,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                contentPadding = PaddingValues(spacing.spaceMedium),
                state = lazyGridState,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                items(state.bookmarks) { bookmarkUi ->
                    BookmarkItem(
                        bookmarkUi = bookmarkUi,
                        modifier = Modifier
                            .wrapContentHeight()
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun BookmarksScreenPreview() {
    MemoryOrganizerTheme {
        BookmarkScreen(
            lazyGridState = rememberLazyGridState(),
            isTopAppBarVisible = true,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
        )
    }
}

internal val previewBookmark = Bookmark(
    title = "Haze 1.0 - Chris Banes",
    url = "https://chrisbanes.me/posts/haze-1.0",
    description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
    imageUrl = "",
).toBookmarkUi()