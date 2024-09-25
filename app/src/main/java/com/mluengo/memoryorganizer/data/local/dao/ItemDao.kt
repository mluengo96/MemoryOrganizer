package com.mluengo.memoryorganizer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mluengo.memoryorganizer.data.local.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemEntity: ItemEntity)

    @Delete
    suspend fun deleteItem(itemEntity: ItemEntity)

    /*@Query(
        """
            SELECT *
            FROM folderentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """
    )
    fun getItem(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>*/

    @Query(
        """
            SELECT *
            FROM itementity
        """
    )
    fun getItems(): Flow<List<ItemEntity>>
}