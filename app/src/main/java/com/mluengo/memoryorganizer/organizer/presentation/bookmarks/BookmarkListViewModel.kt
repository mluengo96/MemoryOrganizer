package com.mluengo.memoryorganizer.organizer.presentation.bookmarks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class BookmarkListViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(
        BookmarkListState(
            bookmarks = (1..10).map {
                previewBookmark
            }
        ),
    )
    val state = _state
}