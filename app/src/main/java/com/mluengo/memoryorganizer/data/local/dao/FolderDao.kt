package com.mluengo.memoryorganizer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mluengo.memoryorganizer.data.local.entity.FolderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folderEntity: FolderEntity)

    @Delete
    suspend fun deleteFolder(folderEntity: FolderEntity)

    /*@Query(
        """
            SELECT *
            FROM folderentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getFolder(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>*/

    @Query(
        """
            SELECT *
            FROM folderentity
        """
    )
    fun getFolders(): Flow<List<FolderEntity>>
}