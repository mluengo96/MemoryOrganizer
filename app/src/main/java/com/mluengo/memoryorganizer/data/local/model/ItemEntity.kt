package com.mluengo.memoryorganizer.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey val id: Int? = null,
    val url: String,
    val title: String,
    val description: String,
    val image: String, // TODO: TBD
    val folderId: Int?,
)
