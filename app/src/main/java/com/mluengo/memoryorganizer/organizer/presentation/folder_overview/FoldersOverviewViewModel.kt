package com.mluengo.memoryorganizer.organizer.presentation.folder_overview

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mluengo.memoryorganizer.core.navigation.HomeRoute
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.domain.repository.FolderDataSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FoldersOverviewViewModel(
    //preferences: Preferences,
    private val savedStateHandle: SavedStateHandle,
    folderDataSource: FolderDataSource,
): ViewModel() {

    // Key used to save and retrieve the currently selected folder id from saved state.
    private val selectedFolderIdKey = "selectedFolderIdKey"

    private val homeRoute: HomeRoute = savedStateHandle.toRoute()
    private val selectedFolderId = savedStateHandle.getStateFlow(
        key = selectedFolderIdKey,
        initialValue = homeRoute.initialFolderId
    )

    val uiState: StateFlow<HomeUiState> = combine(
        selectedFolderId,
        folderDataSource.getFolders(),
        HomeUiState::Folders
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState.Loading
    )

    fun onFolderClick(folderId: String?) {
        savedStateHandle[selectedFolderIdKey] = folderId
    }

    val foldersUiState: StateFlow<FoldersOverviewUiState> =
        folderDataSource.getFolders()
            .map { folders ->
                if (folders.isEmpty()) FoldersOverviewUiState.Empty
                else FoldersOverviewUiState.Success(createdFolders = folders)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = FoldersOverviewUiState.Loading
            )

    // TODO: We don't show onboarding in next app launches
    // TODO: preferences.saveShouldShowOnboarding(false)
}

@Stable
sealed interface FoldersOverviewUiState {
    data object Empty : FoldersOverviewUiState
    data object Loading : FoldersOverviewUiState
    data class Success(val createdFolders: List<Folder>) : FoldersOverviewUiState
}

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Folders(
        val selectedFolderId: String?,
        val folders: List<Folder>,
    ) : HomeUiState

    data object Empty : HomeUiState
}