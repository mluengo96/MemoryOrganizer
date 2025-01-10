package com.mluengo.memoryorganizer.organizer.presentation.folder_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mluengo.memoryorganizer.core.presentation.components.LoadingProgressIndicator
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components.EmptyFolderScreen
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components.FolderItem
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components.HeaderFolders
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.util.FoldersPreviewParameterProvider
import com.mluengo.memoryorganizer.organizer.presentation.models.toFolderUi
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun FoldersOverviewScreen(
    lazyStaggeredGridState: LazyStaggeredGridState,
    onFolderClick: (String) -> Unit,
    viewModel: FoldersOverviewViewModel = koinViewModel()
) {
    val spacing = LocalSpacing.current
    val foldersState by viewModel.uiState.collectAsStateWithLifecycle()

    // Ensure the list always starts at the top when entering this screen
    LaunchedEffect(Unit) {
        lazyStaggeredGridState.scrollToItem(0)
    }

    when (foldersState) {
        HomeUiState.Empty -> { EmptyFolderScreen() }
        HomeUiState.Loading -> { LoadingProgressIndicator() }
        is HomeUiState.Folders -> {
            /*LazyColumn(
                contentPadding = PaddingValues(spacing.spaceMedium),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                state = lazyListState
            ) {
                item { HeaderFolders() }
                items((foldersState as HomeUiState.Folders).folders) { folder ->
                    FolderItem(
                        folderUi = folder,
                        onClick = {
                            viewModel.onFolderClick(folder.id)
                            onFolderClick(folder.id)
                        },
                    )
                }
            }*/
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
                item(span = StaggeredGridItemSpan.FullLine) { HeaderFolders() }
                val testFolders = FoldersPreviewParameterProvider().values.map { folder -> folder.map { it.toFolderUi() } }.toList()
                items((foldersState as HomeUiState.Folders).folders) { folder ->
                    FolderItem(
                        folderUi = folder,
                        onClick = {
                            viewModel.onFolderClick(folder.id)
                            onFolderClick(folder.id)
                        },
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun FolderScreenPreview(
    @PreviewParameter(FoldersPreviewParameterProvider::class)
    folders: List<Folder>
) {
    FoldersOverviewScreen(
        lazyStaggeredGridState = rememberLazyStaggeredGridState(),
        onFolderClick = { }
    )
}