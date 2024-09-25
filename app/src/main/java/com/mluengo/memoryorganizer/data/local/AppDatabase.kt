package com.mluengo.memoryorganizer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mluengo.memoryorganizer.data.local.dao.FolderDao
import com.mluengo.memoryorganizer.data.local.entity.FolderEntity
import com.mluengo.memoryorganizer.data.local.entity.ItemEntity

@Database(
    entities = [FolderEntity::class, ItemEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val dao: FolderDao
}