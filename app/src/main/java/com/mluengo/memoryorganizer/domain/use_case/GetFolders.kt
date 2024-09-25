package com.mluengo.memoryorganizer.domain.use_case

import com.mluengo.memoryorganizer.domain.model.Folder
import com.mluengo.memoryorganizer.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class GetFolders(
    private val repository: FolderRepository
) {
    operator fun invoke(): Flow<List<Folder>> {
        return repository.getFolders()
    }
}