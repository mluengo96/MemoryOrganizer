package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoldersOverviewViewModel @Inject constructor(
    //preferences: Preferences,
    useCases: UseCases
): ViewModel() {

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
}

@Stable
sealed interface FoldersOverviewUiState {
    data object Empty : FoldersOverviewUiState
    data object Loading : FoldersOverviewUiState
    data class Success(val createdFolders: List<Folder>) : FoldersOverviewUiState
}