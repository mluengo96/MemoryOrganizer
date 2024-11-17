package com.mluengo.memoryorganizer.organizer.presentation.models

import com.mluengo.memoryorganizer.organizer.domain.model.Folder

data class FolderUi(
    val id: String,
    val title: String,
    val description: String,
    val status: String? = null,
    val iconResId: Int? = null,
    val itemList: List<String> = emptyList()
)

fun Folder.toFolderUi(): FolderUi {
    return FolderUi(
        id = id,
        title = title,
        description = description,
        status = status,
        iconResId = iconResId,
        itemList = itemList,
    )
}
