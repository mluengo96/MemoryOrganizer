package com.mluengo.memoryorganizer.domain.use_case

import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.repository.FolderRepository

class AddFolder(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        status: String,
        iconResId: Int?,
        itemList: List<Int>
    ) {
        repository.insertFolder(
            Folder(
                title = title,
                description = description,
                status = status,
                iconResId = iconResId,
                itemList = itemList
            )
        )
    }
}
