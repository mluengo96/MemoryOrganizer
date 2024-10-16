package com.mluengo.memoryorganizer.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FolderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val status: String,
    val iconResId: Int?,
    val itemList: List<String>
)
