package com.mluengo.memoryorganizer.organizer.domain.model

data class Bookmark(
    val id: Int? = null,
    val title: String,
    val url: String,
    val description: String,
    val imageUrl: String, // TODO: TBD
    val folderId: Int? = null,
)