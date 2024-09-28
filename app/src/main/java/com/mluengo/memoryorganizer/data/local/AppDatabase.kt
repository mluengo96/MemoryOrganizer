package com.mluengo.memoryorganizer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mluengo.memoryorganizer.data.local.dao.FolderDao
import com.mluengo.memoryorganizer.data.local.entity.FolderEntity
import com.mluengo.memoryorganizer.data.local.entity.ItemEntity

@Database(
    entities = [FolderEntity::class, ItemEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: FolderDao
}