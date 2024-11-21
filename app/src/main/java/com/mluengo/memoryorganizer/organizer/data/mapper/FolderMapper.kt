package com.mluengo.memoryorganizer.organizer.data.mapper

import com.mluengo.memoryorganizer.organizer.data.local.model.FolderEntity
import com.mluengo.memoryorganizer.organizer.domain.model.Folder

fun FolderEntity.toFolder(): Folder {
    return Folder(
        id = id,
        title = title,
        description = description,
        status = status,
        iconResId = iconResId,
        itemList = itemList
    )
}

fun Folder.toFolderEntity(): FolderEntity {
    return FolderEntity(
        id = id,
        title = title,
        description = description,
        status = status,
        iconResId = iconResId,
        itemList = itemList
    )
}