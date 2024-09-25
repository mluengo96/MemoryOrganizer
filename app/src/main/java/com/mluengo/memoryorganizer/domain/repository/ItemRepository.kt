package com.mluengo.memoryorganizer.domain.repository

import com.mluengo.memoryorganizer.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun insertItem(item: Item)
    suspend fun deletedItem(item: Item)
    // TODO: fun getItem
    fun getItems(): Flow<List<Item>>
}