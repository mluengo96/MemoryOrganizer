package com.mluengo.memoryorganizer.organizer.data.local.repository

import com.mluengo.memoryorganizer.organizer.data.local.dao.FolderDao
import com.mluengo.memoryorganizer.organizer.data.mapper.toFolder
import com.mluengo.memoryorganizer.organizer.data.mapper.toFolderEntity
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FolderRepositoryImpl(
    private val dao: FolderDao
): FolderRepository {
    override suspend fun insertFolder(folder: Folder) {
        dao.insertFolder(folder.toFolderEntity())
    }

    override suspend fun deletedFolder(folder: Folder) {
        dao.deleteFolder(folder.toFolderEntity())
    }

    override fun getFolderDetail(id: String): Flow<Folder> {
        return dao.getFolderDetail(id).map { folder ->
            folder.toFolder()
        }
    }

    override fun getFolders(): Flow<List<Folder>> {
        return dao.getFolders().map { folders ->
            folders.map { it.toFolder() }
        }
    }
}