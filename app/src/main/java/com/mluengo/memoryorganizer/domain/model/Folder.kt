package com.mluengo.memoryorganizer.domain.model

data class Folder(
    val id: Int = 0,
    val title: String,
    val description: String,
    val status: String,
    val iconResId: Int?,
    val itemList: List<String>
)
