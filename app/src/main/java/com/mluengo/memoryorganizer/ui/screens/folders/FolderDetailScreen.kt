package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.components.TopAppBar
import com.mluengo.memoryorganizer.ui.components.cards.ItemCard
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun FolderDetailScreen(
    lazyListState: LazyListState,
    isTopAppBarVisible: Boolean,
    onNavigateUp: () -> Unit,
    viewModel: FolderDetailViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val folderDetailState by viewModel.folderUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(0)  // Ensure the list always starts at the top when entering this screen
    }

    when (folderDetailState) {
        FolderDetailUiState.Error -> TODO()
        FolderDetailUiState.Loading -> { Unit }
        is FolderDetailUiState.Success -> {
            val details = (folderDetailState as FolderDetailUiState.Success).folder
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                TopAppBar(
                    title = details.title,
                    hasNavigationButton = true,
                    actionIcon = Icons.Rounded.MoreVert,
                    actionIconContentDescription = stringResource(id = R.string.edit),
                    onActionClick = { /* TODO */ },
                    navigationIconContentDescription = stringResource(id = R.string.back),
                    onNavigationClick = onNavigateUp,
                    lazyListState = lazyListState,
                    isVisible = isTopAppBarVisible,
                )
                Column {
                    LazyColumn(
                        contentPadding = PaddingValues(spacing.spaceMedium),
                        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                        state = lazyListState
                    ) {
                        item {
                            Text(
                                text = details.description,
                                textAlign = TextAlign.Start,
                                style = MemoryOrganizerTypography.bodyMedium,
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceLarge))
                        }

                        val testLinks = listOf(
                            "https://not-valid-url", // --> Invalid URL
                            "https://m3.material.io/develop/android/jetpack-compose", // --> Valid URL
                            "https://expatexplore.com/blog/when-to-travel-weather-seasons/", // --> URL that does not contain image
                        )

                        // Add 5 items
                        items(testLinks) { link ->
                            ItemCard(
                                title = "test",
                                description = "test description",
                                imageUrl = ""
                                //link = link,
                            )
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
        lazyListState = rememberLazyListState(),
        isTopAppBarVisible = true,
        onNavigateUp = { }
    )
}