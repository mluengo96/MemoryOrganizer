package com.mluengo.memoryorganizer.domain.model

data class Item(
    val id: Int? = null,
    val title: String,
    val url: String,
    val description: String,
    val image: String, // TODO: TBD
    val folderId: Int?,
)
