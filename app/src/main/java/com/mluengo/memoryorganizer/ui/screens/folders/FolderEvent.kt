package com.mluengo.memoryorganizer.ui.screens.folders

sealed class FolderEvent {
    data class OnCreateFolderClick(
        val title: String,
        val description: String,
        val iconResId: Int?,
        val itemList: List<Int>
    ): FolderEvent()
}