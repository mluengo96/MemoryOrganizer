package com.mluengo.memoryorganizer.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines a folder a user may create.
 * It has a one to many relationship with [ItemEntity]
 */
@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val status: String,
    val iconResId: Int?,
    val itemList: List<String>
)
