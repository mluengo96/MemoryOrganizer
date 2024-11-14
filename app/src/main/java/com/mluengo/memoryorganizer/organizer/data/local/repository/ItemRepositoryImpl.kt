package com.mluengo.memoryorganizer.organizer.data.local.repository

import com.mluengo.memoryorganizer.organizer.data.local.dao.ItemDao
import com.mluengo.memoryorganizer.organizer.data.mapper.toItem
import com.mluengo.memoryorganizer.organizer.data.mapper.toItemEntity
import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.organizer.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl(
    private val dao: ItemDao
): ItemRepository {
    override suspend fun insertItem(bookmark: Bookmark) {
        dao.insertItem(bookmark.toItemEntity())
    }

    override suspend fun deletedItem(bookmark: Bookmark) {
        dao.deleteItem(bookmark.toItemEntity())
    }

    override fun getItemsFromFolder(folderId: Int): Flow<List<Bookmark>> {
        return dao.getItemsFromFolder(folderId).map { bookmarks ->
            bookmarks.map { it.toItem() }
        }
    }

    override fun getItems(): Flow<List<Bookmark>> {
        return dao.getItems().map { items ->
            items.map { it.toItem() }
        }
    }
}