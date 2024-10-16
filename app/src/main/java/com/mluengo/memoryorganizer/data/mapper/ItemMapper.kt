package com.mluengo.memoryorganizer.data.mapper

import com.mluengo.memoryorganizer.data.local.model.ItemEntity
import com.mluengo.memoryorganizer.domain.model.Bookmark

fun ItemEntity.toItem(): Bookmark {
    return Bookmark(
        id = id,
        title = title,
        url = url,
        description = description,
        image = image,
        folderId = folderId
    )
}

fun Bookmark.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = id,
        url = url,
        title = title,
        description = description,
        image = image,
        folderId = folderId
    )
}