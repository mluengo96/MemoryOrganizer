package com.mluengo.memoryorganizer.organizer.presentation.folder_overview

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mluengo.memoryorganizer.core.presentation.components.LoadingProgressIndicator
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.components.FolderCard
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components.EmptyFolderScreen
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.components.HeaderFolders
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun FoldersOverviewScreen(
    lazyListState: LazyListState,
    onFolderClick: (String) -> Unit,
    viewModel: FoldersOverviewViewModel = koinViewModel()
) {
    val spacing = LocalSpacing.current
    val foldersState by viewModel.uiState.collectAsStateWithLifecycle()

    // Ensure the list always starts at the top when entering this screen
    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(0)
    }

    when (foldersState) {
        HomeUiState.Empty -> { EmptyFolderScreen() }
        HomeUiState.Loading -> { LoadingProgressIndicator() }
        is HomeUiState.Folders -> {
            LazyColumn(
                contentPadding = PaddingValues(spacing.spaceMedium),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                state = lazyListState
            ) {
                item { HeaderFolders() }
                items((foldersState as HomeUiState.Folders).folders) { folder ->
                    FolderCard(
                        folder = folder,
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