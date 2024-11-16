package com.mluengo.memoryorganizer.organizer.presentation.models

import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark

data class BookmarkUi(
    val title: String,
    val description: String,
    val imageUrl: String,
)

fun Bookmark.toBookmarkUi(): BookmarkUi {
    return BookmarkUi(
        title = title,
        description = description,
        imageUrl = imageUrl
    )
}