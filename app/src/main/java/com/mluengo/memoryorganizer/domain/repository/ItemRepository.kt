package com.mluengo.memoryorganizer.domain.repository

import com.mluengo.memoryorganizer.domain.model.Bookmark
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun insertItem(bookmark: Bookmark)
    suspend fun deletedItem(bookmark: Bookmark)
    fun getItemsFromFolder(folderId: Int): Flow<List<Bookmark>>
    fun getItems(): Flow<List<Bookmark>>
}