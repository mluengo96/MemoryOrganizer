package com.mluengo.memoryorganizer.domain.model

data class Folder(
    val id: Int? = null,
    val title: String,
    val description: String,
    val iconResId: Int?,
    val itemList: List<Int>
)
