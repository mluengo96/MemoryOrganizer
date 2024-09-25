package com.mluengo.memoryorganizer.domain.use_case

import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.repository.FolderRepository

class DeleteFolder(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(
        folder: Folder
    ) {
        repository.deletedFolder(folder)
    }
}
