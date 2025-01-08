package com.mluengo.memoryorganizer.organizer.presentation.bookmarks.util

import com.mluengo.memoryorganizer.organizer.domain.model.Bookmark
import com.mluengo.memoryorganizer.organizer.presentation.models.BookmarkUi
import com.mluengo.memoryorganizer.organizer.presentation.models.toBookmarkUi

internal class BookmarksPreviewParameterProvider {
    val values: List<BookmarkUi>
        get() = listOf(
            Bookmark(
                title = "Haze 1.0 - Chris Banes",
                url = "https://chrisbanes.me/posts/haze-1.0",
                description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
                imageUrl = "",
            ).toBookmarkUi(),
            Bookmark(
                title = "Haze 1.0 - Chris Banes",
                url = "https://not-valid-url",
                description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
                imageUrl = "",
            ).toBookmarkUi(),
            Bookmark(
                title = "Haze 1.0 - Chris Banes",
                url = "https://chrisbanes.me/posts/haze-1.0",
                description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
                imageUrl = "",
            ).toBookmarkUi(),
            Bookmark(
                title = "Haze 1.0 - Chris Banes",
                url = "https://expatexplore.com/blog/when-to-travel-weather-seasons/",
                description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
                imageUrl = "",
            ).toBookmarkUi(),
            Bookmark(
                title = "Haze 1.0 - Chris Banes",
                url = "https://chrisbanes.me/posts/haze-1.0",
                description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
                imageUrl = "",
            ).toBookmarkUi(),
            Bookmark(
                title = "Haze 1.0 - Chris Banes",
                url = "https://not-valid-url",
                description = "Haze 1.0 is a powerful library for achieving background blurring effects within Jetpack Compose and Compose Multiplatform apps.",
                imageUrl = "",
            ).toBookmarkUi()
        )

}