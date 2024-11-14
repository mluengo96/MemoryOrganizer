package com.mluengo.memoryorganizer.organizer.data.local.dao

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface FolderItemDao {
    @Query(
        """
            SELECT folderEntity.id AS folderId, itemEntity.id AS itemId
            FROM folderentity, itementity
            WHERE folderentity.id = itemEntity.folderId
        """
    )
    fun getFolderAndItems(): Flow<List<FolderItem>>
}

data class FolderItem(val folderId: Int?, val itemId: Int?)