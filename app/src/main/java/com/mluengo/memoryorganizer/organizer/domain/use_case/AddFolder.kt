package com.mluengo.memoryorganizer.organizer.domain.use_case

import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.domain.repository.FolderRepository

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
