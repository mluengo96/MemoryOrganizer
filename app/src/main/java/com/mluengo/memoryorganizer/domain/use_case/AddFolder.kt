package com.mluengo.memoryorganizer.domain.use_case

import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.repository.FolderRepository

class AddFolder(
    private val repository: FolderRepository
) {
    suspend operator fun invoke(
        folder: Folder
    ) {
        repository.insertFolder(
            folder = folder
        )
    }
}
