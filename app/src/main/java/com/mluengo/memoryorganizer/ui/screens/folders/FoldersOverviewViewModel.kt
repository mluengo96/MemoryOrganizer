package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.use_case.UseCases
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.util.Constants.FOLDER_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoldersOverviewViewModel @Inject constructor(
    //preferences: Preferences,
    private val savedStateHandle: SavedStateHandle,
    useCases: UseCases,
): ViewModel() {
    private val route = savedStateHandle.toRoute<Route.Folder>()

    /*val selectedFolderId: StateFlow<Int?> = savedStateHandle.getStateFlow(
        key = FOLDER_ID_KEY,
        initialValue = route.folderId
    )*/

    val foldersUiState: StateFlow<FoldersOverviewUiState> =
        useCases.getFolders()
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

    fun onFolderClick(folderId: Int) {
        savedStateHandle[FOLDER_ID_KEY] = folderId
    }
}

@Stable
sealed interface FoldersOverviewUiState {
    data object Empty : FoldersOverviewUiState
    data object Loading : FoldersOverviewUiState
    data class Success(val createdFolders: List<Folder>) : FoldersOverviewUiState
}