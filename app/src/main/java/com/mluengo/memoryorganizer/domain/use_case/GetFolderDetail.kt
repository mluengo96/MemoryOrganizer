package com.mluengo.memoryorganizer.domain.use_case

import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class GetFolderDetail(
    private val repository: FolderRepository
) {
    operator fun invoke(
        folderId: String
    ): Flow<Folder> {
        return repository.getFolderDetail(
            id = folderId
        )
    }
}