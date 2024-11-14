package com.mluengo.memoryorganizer.organizer.domain.repository

import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    suspend fun insertFolder(folder: Folder)
    suspend fun deletedFolder(folder: Folder)
    fun getFolderDetail(id: String): Flow<Folder>
    fun getFolders(): Flow<List<Folder>>
}