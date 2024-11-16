package com.mluengo.memoryorganizer.organizer.presentation.folder_detail

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.domain.repository.FolderDataSource
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.navigation.FolderRoute
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FolderDetailViewModel(
    //preferences: Preferences,
    savedStateHandle: SavedStateHandle,
    folderDataSource: FolderDataSource
): ViewModel() {

    val folderId = savedStateHandle.toRoute<FolderRoute>().id

    val folderUiState: StateFlow<FolderDetailUiState> =
        folderDataSource.getFolderDetail(folderId)
            .map { folderDetail ->
                FolderDetailUiState.Success(
                    folder = Folder(
                        id = folderDetail.id,
                        title = folderDetail.title,
                        description = folderDetail.description,
                        status = folderDetail.status,
                        iconResId = folderDetail.iconResId,
                        itemList = folderDetail.itemList
                    )
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FolderDetailUiState.Loading
            )
}

@Stable
sealed interface FolderDetailUiState {
    data object Error : FolderDetailUiState
    data object Loading : FolderDetailUiState
    data class Success(val folder: Folder) : FolderDetailUiState
}

@Stable
sealed interface BookmarksUiState {
    data object Error : BookmarksUiState
    data object Loading : BookmarksUiState
    data class Success(val bookmarks: List<Bookmark>) : BookmarksUiState
}