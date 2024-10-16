package com.mluengo.memoryorganizer.domain.use_case

import com.mluengo.memoryorganizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarksFromFolder(
    private val repository: ItemRepository
) {
    operator fun invoke(
        folderId: Int
    ): Flow<List<Bookmark>> {
        return repository.getItemsFromFolder(folderId = folderId)
    }
}