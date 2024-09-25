package com.mluengo.memoryorganizer.data.local.repository

import com.mluengo.memoryorganizer.data.local.dao.ItemDao
import com.mluengo.memoryorganizer.data.mapper.toItem
import com.mluengo.memoryorganizer.data.mapper.toItemEntity
import com.mluengo.memoryorganizer.domain.model.Item
import com.mluengo.memoryorganizer.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl(
    private val dao: ItemDao
): ItemRepository {
    override suspend fun insertItem(item: Item) {
        dao.insertItem(item.toItemEntity())
    }

    override suspend fun deletedItem(item: Item) {
        dao.deleteItem(item.toItemEntity())
    }

    override fun getItems(): Flow<List<Item>> {
        return dao.getItems().map { items ->
            items.map { it.toItem() }
        }
    }
}