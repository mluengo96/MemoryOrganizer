package com.mluengo.memoryorganizer.data.mapper

import com.mluengo.memoryorganizer.data.local.entity.ItemEntity
import com.mluengo.memoryorganizer.domain.model.Item

fun ItemEntity.toItem(): Item {
    return Item(
        id = id,
        title = title,
        url = url,
        description = description,
        image = image,
        folderId = folderId
    )
}

fun Item.toItemEntity(): ItemEntity {
    return ItemEntity(
        id = id,
        url = url,
        title = title,
        description = description,
        image = image,
        folderId = folderId
    )
}