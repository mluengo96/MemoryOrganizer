package com.mluengo.memoryorganizer.organizer.data.mapper

import com.mluengo.memoryorganizer.organizer.data.local.model.ItemEntity
import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark

fun ItemEntity.toItem(): Bookmark {
    return Bookmark(
        id = id,
        title = title,
        url = url,
        description = description,
        imageUrl = imageUrl,
        folderId = folderId
    )
}

fun Bookmark.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = id,
        url = url,
        title = title,
        description = description,
        imageUrl = imageUrl,
        folderId = folderId
    )
}