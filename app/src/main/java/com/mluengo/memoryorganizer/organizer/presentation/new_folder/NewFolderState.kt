package com.mluengo.memoryorganizer.organizer.presentation.new_folder

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class NewFolderState @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val title: String = "",
    val description: String = "",
    val iconResId: Int? = null,
    val status: String = "",
    val itemList: List<String> = emptyList(),
)