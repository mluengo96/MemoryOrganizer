package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluengo.memoryorganizer.domain.use_case.UseCases
import com.mluengo.memoryorganizer.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewFolderViewModel @Inject constructor(
    //preferences: Preferences,
    private val useCases: UseCases
): ViewModel() {

    var state by mutableStateOf(NewFolderState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FolderEvent) {
        when(event) {
            is FolderEvent.OnCreateFolderClick -> {
                createFolder(event)
            }
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

    // TODO
    fun onStatusSelected() {

    }

    private fun createFolder(event: FolderEvent.OnCreateFolderClick) {
        viewModelScope.launch {
            useCases.addFolder(
                title = event.title,
                description = event.description,
                iconResId = event.iconResId,
                itemList = event.itemList
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
}