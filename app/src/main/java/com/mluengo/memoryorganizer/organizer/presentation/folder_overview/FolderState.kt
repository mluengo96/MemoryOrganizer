package com.mluengo.memoryorganizer.organizer.presentation.folder_overview

data class FolderState(
    val title: String = "",
    val description: String = "",
    val iconResId: Int? = null,
    val status: String = "",
    val itemList: List<Int> = emptyList(),
)