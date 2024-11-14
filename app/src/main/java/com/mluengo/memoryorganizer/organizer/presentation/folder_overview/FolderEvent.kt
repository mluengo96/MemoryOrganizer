package com.mluengo.memoryorganizer.organizer.presentation.folder_overview

import com.mluengo.memoryorganizer.organizer.domain.model.Folder

sealed class FolderEvent {
    data class OnCreateFolderClick(
        val folder: Folder
    ): FolderEvent()
    data class OnGetFolderDetails(val id: Int): FolderEvent()
}