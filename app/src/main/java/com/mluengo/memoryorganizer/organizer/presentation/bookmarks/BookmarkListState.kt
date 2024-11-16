package com.mluengo.memoryorganizer.organizer.presentation.bookmarks

import androidx.compose.runtime.Immutable
import com.mluengo.memoryorganizer.organizer.presentation.models.BookmarkUi

@Immutable
data class BookmarkListState(
    val isLoading: Boolean = false,
    val bookmarks: List<BookmarkUi> = emptyList(),
    //val selectedBookmark: BookmarkUi? = null,
)