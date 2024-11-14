package com.mluengo.memoryorganizer.organizer.domain.use_case

import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.organizer.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetBookmarks(
    private val repository: ItemRepository
) {
    operator fun invoke(

    ): Flow<List<Bookmark>> {
        return repository.getItems()
    }
}