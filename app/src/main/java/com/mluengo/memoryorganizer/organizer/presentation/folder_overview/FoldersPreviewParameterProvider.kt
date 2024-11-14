package com.mluengo.memoryorganizer.organizer.presentation.folder_overview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mluengo.memoryorganizer.organizer.domain.model.Folder


internal class FoldersPreviewParameterProvider: PreviewParameterProvider<List<Folder>> {
    override val values: Sequence<List<Folder>>
        get() = sequenceOf(
            listOf(
                Folder(
                    id = "0",
                    title = "Android development",
                    description = "Android development resources",
                    status = "In Progress",
                    iconResId = null,
                    itemList = listOf("1", "2", "3", "4")
                ),
                Folder(
                    id = "0",
                    title = "iOS development",
                    description = "iOS development resources",
                    status = "Todo",
                    iconResId = null,
                    itemList = listOf()
                ),
                Folder(
                    id = "0",
                    title = "Blender tutorials",
                    description = "",
                    status = "Todo",
                    iconResId = null,
                    itemList = listOf("1", "2", "3", "4")
                ),
                Folder(
                    id = "0",
                    title = "Christmas special recipe",
                    description = "",
                    status = "Done",
                    iconResId = null,
                    itemList = listOf("1", "2")
                ),
            )
        )
}