package com.mluengo.memoryorganizer.core.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.mluengo.memoryorganizer.core.navigation.HomeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

const val FOLDER_ID_KEY = "folderId"

@HiltViewModel
class Folders2PaneViewModel @Inject constructor(
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