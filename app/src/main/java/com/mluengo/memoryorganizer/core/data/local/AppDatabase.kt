package com.mluengo.memoryorganizer.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mluengo.memoryorganizer.organizer.data.local.dao.FolderDao
import com.mluengo.memoryorganizer.organizer.data.local.dao.ItemDao
import com.mluengo.memoryorganizer.organizer.data.local.model.FolderEntity
import com.mluengo.memoryorganizer.organizer.data.local.model.ItemEntity

@Database(
    entities = [FolderEntity::class, ItemEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val foldersDao: FolderDao
    abstract val bookmarksDao: ItemDao
}