package com.mluengo.memoryorganizer.domain.use_case

import androidx.compose.ui.graphics.vector.ImageVector
import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.repository.FolderRepository

class AddFolder(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(
        title: String,
        iconResId: ImageVector,
        itemList: List<Int>
    ) {
        repository.insertFolder(
            Folder(
                title = title,
                iconResId = iconResId,
                itemList = itemList
            )
        )
    }
}
