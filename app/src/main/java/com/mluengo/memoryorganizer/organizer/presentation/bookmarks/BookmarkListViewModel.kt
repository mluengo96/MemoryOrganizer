package com.mluengo.memoryorganizer.organizer.presentation.bookmarks

import androidx.lifecycle.ViewModel
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.util.BookmarksPreviewParameterProvider
import kotlinx.coroutines.flow.MutableStateFlow

class BookmarkListViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(
        BookmarkListState(
            bookmarks = BookmarksPreviewParameterProvider().values
        ),
    )
    val state = _state
}