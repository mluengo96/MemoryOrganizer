package com.mluengo.memoryorganizer.core.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.mluengo.memoryorganizer.core.navigation.HomeRoute
import kotlinx.coroutines.flow.StateFlow

const val FOLDER_ID_KEY = "folderId"

class Folders2PaneViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val route = savedStateHandle.toRoute<HomeRoute>()
    val selectedFolderId: StateFlow<String?> = savedStateHandle.getStateFlow(
        key = FOLDER_ID_KEY,
        initialValue = route.initialFolderId,
    )

    fun onFolderClick(folderId: String?) {
        savedStateHandle[FOLDER_ID_KEY] = folderId
    }
}