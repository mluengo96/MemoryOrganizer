package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mluengo.memoryorganizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.use_case.UseCases
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderDetailViewModel @Inject constructor(
    //preferences: Preferences,
    private val savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
): ViewModel() {
    private val _detailUiState = MutableStateFlow(NewFolderState())
    val detailUiState = _detailUiState.asStateFlow()

    val folder = savedStateHandle.toRoute<Route.Folder>()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FolderEvent) {
        when(event) {
            is FolderEvent.OnGetFolderDetails -> {
                getDetails(event)
            }
            else -> Unit
        }
    }

    val folderDetailUiState: StateFlow<FolderDetailUiState> =
        useCases.getFolderDetail(folder.folderId!!)
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

    val bookmarksUiState: StateFlow<BookmarksUiState> =
        useCases.getBookmarksFromFolder(folderId = folder.folderId!!)
            .map { bookmarks ->
                BookmarksUiState.Success(bookmarks = bookmarks)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BookmarksUiState.Loading
            )

    private fun getDetails(event: FolderEvent.OnGetFolderDetails) {
        viewModelScope.launch {
            useCases.getFolderDetail(
                folderId = event.id
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
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