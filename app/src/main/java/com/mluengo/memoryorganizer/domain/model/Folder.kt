package com.mluengo.memoryorganizer.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Folder(
    val id: Int? = null,
    val title: String,
    val iconResId: ImageVector?,
    val itemList: List<Int>
)
