package com.mluengo.memoryorganizer.organizer.presentation.folder_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.core.presentation.components.BackToTopButton
import com.mluengo.memoryorganizer.core.presentation.components.MoTopAppBar
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.components.BookmarkItem
import com.mluengo.memoryorganizer.organizer.presentation.models.BookmarkUi
import com.mluengo.memoryorganizer.organizer.presentation.models.toFolderUi
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun FolderDetailScreen(
    lazyGridState: LazyGridState,
    isTopAppBarVisible: Boolean,
    onNavigateUp: () -> Unit,
    viewModel: FolderDetailViewModel = koinViewModel()
) {
    val spacing = LocalSpacing.current
    val state by viewModel.folderUiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Ensure the list always starts at the top when entering this screen
        lazyGridState.scrollToItem(0)
    }

    when (state) {
        FolderDetailUiState.Error -> TODO()
        FolderDetailUiState.Loading -> { Unit }
        is FolderDetailUiState.Success -> {
            val details = (state as FolderDetailUiState.Success).folder
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                MoTopAppBar(
                    title = details.title,
                    hasNavigationButton = true,
                    actionIcon = Icons.Rounded.MoreVert,
                    actionIconContentDescription = stringResource(id = R.string.edit),
                    onActionClick = { /* TODO */ },
                    navigationIconContentDescription = stringResource(id = R.string.back),
                    onNavigationClick = onNavigateUp,
                    isVisible = isTopAppBarVisible,
                )
                Column {
                    val columns = 2
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(columns),
                        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                        contentPadding = PaddingValues(spacing.spaceMedium),
                        state = lazyGridState,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        item(span = { GridItemSpan(columns) }) {
                            Text(
                                text = details.description,
                                textAlign = TextAlign.Start,
                                style = MemoryOrganizerTypography.bodyMedium,
                            )
                        }
                        item(span = { GridItemSpan(columns) }) {
                            Spacer(modifier = Modifier.height(spacing.spaceLarge))
                        }

                        val testLinks = listOf(
                            "https://not-valid-url", // --> Invalid URL
                            "https://m3.material.io/develop/android/jetpack-compose", // --> Valid URL
                            "https://expatexplore.com/blog/when-to-travel-weather-seasons/", // --> URL that does not contain image
                        )

                        // Add 5 items
                        items(testLinks) { link ->
                            BookmarkItem(
                                bookmarkUi = BookmarkUi(
                                    title = "test",
                                    description = "test description",
                                    imageUrl = ""
                                ),
                                //link = link,
                            )
                        }

                        item(span = { GridItemSpan(columns) }) {
                            BackToTopButton(modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = spacing.spaceExtraLarge,
                                    vertical = spacing.spaceMedium
                                )) {
                                coroutineScope.launch {
                                    lazyGridState.animateScrollToItem(0)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun ItemScreenPreview() {
    FolderDetailScreen(
        lazyGridState = rememberLazyGridState(),
        isTopAppBarVisible = true,
        onNavigateUp = { }
    )
}

internal val previewFolder = Folder(
    id = "1",
    title = "Android development",
    description = "This folder stores important Android resources for developers.",
    status = "",
    iconResId = null,
    itemList = listOf(),
).toFolderUi()