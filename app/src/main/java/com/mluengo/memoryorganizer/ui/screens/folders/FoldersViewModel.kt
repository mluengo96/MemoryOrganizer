package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluengo.memoryorganizer.domain.use_case.UseCases
import com.mluengo.memoryorganizer.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class FoldersViewModel @Inject constructor(
    //preferences: Preferences,
    private val useCases: UseCases
): ViewModel() {
    var state by mutableStateOf(FoldersOverviewState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoldersJob: Job? = null

    // TODO: We don't show onboarding in next app launches
    init {
        getFolders()
        // TODO: preferences.saveShouldShowOnboarding(false)
    }

    private fun getFolders() {
        getFoldersJob?.cancel()
        getFoldersJob = useCases
            .getFolders()
            .onEach { folders ->
                state = state.copy(
                    createdFolders = folders
                )
            }
            .launchIn(viewModelScope)

    }
}