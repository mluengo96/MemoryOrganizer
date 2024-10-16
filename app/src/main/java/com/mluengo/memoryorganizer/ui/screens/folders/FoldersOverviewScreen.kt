package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.ui.components.HeaderFolders
import com.mluengo.memoryorganizer.ui.components.cards.FolderCard
import com.mluengo.memoryorganizer.ui.components.empty.EmptyFolderScreen
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun FoldersOverviewScreen(
    lazyListState: LazyListState,
    onFolderClick: (Int) -> Unit,
    viewModel: FoldersOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val foldersState by viewModel.foldersUiState.collectAsStateWithLifecycle()

    // Ensure the list always starts at the top when entering this screen
    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(0)
    }

    when (foldersState) {
        FoldersOverviewUiState.Empty -> { EmptyFolderScreen() }
        FoldersOverviewUiState.Loading -> { Unit }
        is FoldersOverviewUiState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(spacing.spaceMedium),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                state = lazyListState
            ) {
                item {
                    HeaderFolders()
                }

                items((foldersState as FoldersOverviewUiState.Success).createdFolders) { folder ->
                    FolderCard(
                        onClick = {
                            viewModel.onFolderClick(folder.id)
                            onFolderClick(folder.id)
                        },
                        title = folder.title,
                        status = folder.status,
                        itemSize = folder.itemList.size
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun FolderScreenPreview(
    @PreviewParameter(FoldersPreviewParameterProvider::class)
    folders: List<Folder>
) {
    FoldersOverviewScreen(
        lazyListState = rememberLazyListState(),
        onFolderClick = { }
    )
}