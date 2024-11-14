package com.mluengo.memoryorganizer.organizer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mluengo.memoryorganizer.organizer.data.local.model.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Upsert
    suspend fun insertItem(itemEntity: ItemEntity)

    @Delete
    suspend fun deleteItem(itemEntity: ItemEntity)

    @Query(
        """
            SELECT *
            FROM itementity
            WHERE folderId = :folderId
        """
    )
    fun getItemsFromFolder(folderId: Int): Flow<List<ItemEntity>>

    @Query(
        """
            SELECT *
            FROM itementity
        """
    )
    fun getItems(): Flow<List<ItemEntity>>
}