package com.mluengo.memoryorganizer.organizer.presentation.folder_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
    lazyStaggeredGridState: LazyStaggeredGridState,
    isTopAppBarVisible: Boolean,
    onNavigateUp: () -> Unit,
    viewModel: FolderDetailViewModel = koinViewModel()
) {
    val spacing = LocalSpacing.current
    val state by viewModel.folderUiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        // Ensure the list always starts at the top when entering this screen
        lazyStaggeredGridState.scrollToItem(0)
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
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(columns),
                        verticalItemSpacing = spacing.spaceSmall,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                        contentPadding = PaddingValues(spacing.spaceMedium),
                        state = lazyStaggeredGridState,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        item(span = StaggeredGridItemSpan.FullLine) {
                            Text(
                                text = details.description,
                                textAlign = TextAlign.Start,
                                style = MemoryOrganizerTypography.bodyLarge,
                            )
                        }
                        item(span = StaggeredGridItemSpan.FullLine) {
                            Spacer(modifier = Modifier.height(spacing.spaceLarge))
                        }

                        val testLinks = listOf(
                            //"https://not-valid-url", // --> Invalid URL
                            "https://theobjective.com/espana/politica/2025-01-08/csd-cautelar-fc-barcelona-dani-olmo/",
                            "https://m3.material.io/develop/android/jetpack-compose", // --> Valid URL
                            "https://composelibraries.com/", // --> URL that does not contain image
                            "https://culturedcode.com/things/",
                            "https://thesachee.carrd.co/"
                        )

                        // Add 5 items
                        items(testLinks) { link ->
                            BookmarkItem(
                                bookmarkUi = BookmarkUi(
                                    title = "test",
                                    description = "test description",
                                    imageUrl = "",
                                    url = link
                                ),
                            )
                        }

                        item(span = StaggeredGridItemSpan.FullLine) {
                            BackToTopButton(modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = spacing.spaceExtraLarge,
                                    vertical = spacing.spaceMedium
                                )) {
                                coroutineScope.launch {
                                    lazyStaggeredGridState.animateScrollToItem(0)
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
        lazyStaggeredGridState = rememberLazyStaggeredGridState(),
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