package com.mluengo.memoryorganizer.organizer.presentation.new_folder

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluengo.memoryorganizer.core.presentation.util.UiEvent
import com.mluengo.memoryorganizer.organizer.domain.repository.FolderDataSource
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.FolderEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NewFolderViewModel(
    //preferences: Preferences
    private val folderDataSource: FolderDataSource,
): ViewModel() {

    var state by mutableStateOf(NewFolderState())
        private set

    val titleIsEmpty by derivedStateOf {
        state.title.isBlank()
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FolderEvent) {
        when(event) {
            is FolderEvent.OnCreateFolderClick -> {
                createFolder(event)
            }
            else -> Unit
        }
    }

    fun onTitleAdded(folderTitle: String) {
        state = state.copy(title = folderTitle)
    }

    fun onDescriptionAdded(folderDescription: String) {
        state = state.copy(description = folderDescription)
    }

    fun onIconAdded(icon: Int) {
        state = state.copy(iconResId = icon)
    }

    fun onTitleCleared() {
        state = state.copy(title = "")
    }

    fun onStatusSelected(status: String) {
        state = state.copy(status = status)
    }

    private fun createFolder(event: FolderEvent.OnCreateFolderClick) {
        viewModelScope.launch {
            folderDataSource.insertFolder(
                event.folder
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
}