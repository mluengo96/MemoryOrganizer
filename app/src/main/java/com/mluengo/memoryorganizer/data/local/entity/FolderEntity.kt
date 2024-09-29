package com.mluengo.memoryorganizer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FolderEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val status: String,
    val iconResId: Int?,
    val itemList: List<Int>
)
