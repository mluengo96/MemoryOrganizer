package com.mluengo.memoryorganizer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mluengo.memoryorganizer.data.local.model.FolderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Upsert
    suspend fun insertFolder(folderEntity: FolderEntity)

    @Delete
    suspend fun deleteFolder(folderEntity: FolderEntity)

    @Query(
        """
            SELECT *
            FROM folders
            WHERE id = :id
        """
    )
    fun getFolderDetail(id: String): Flow<FolderEntity>

    @Query(
        """
            SELECT *
            FROM folders
        """
    )
    fun getFolders(): Flow<List<FolderEntity>>
}