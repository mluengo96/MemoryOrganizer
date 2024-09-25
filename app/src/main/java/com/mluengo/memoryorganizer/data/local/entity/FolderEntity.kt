package com.mluengo.memoryorganizer.data.local.entity

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FolderEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val iconResId: ImageVector?,
    val itemList: List<Int>
)
