package com.mluengo.memoryorganizer.ui.screens.folders

import com.mluengo.memoryorganizer.domain.model.Folder

sealed class FolderEvent {
    data class OnCreateFolderClick(
        val folder: Folder
    ): FolderEvent()
    data class OnGetFolderDetails(val id: Int): FolderEvent()
}