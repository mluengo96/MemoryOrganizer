package com.mluengo.memoryorganizer.navigation.bookmarks

import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.memoryorganizer.ui.screens.bookmarks.BookmarkScreen
import kotlinx.serialization.Serializable

@Serializable data object BookmarksRoute

fun NavController.navigateToBookmarks(navOptions: NavOptions? = null) = navigate(route = BookmarksRoute, navOptions)

fun NavGraphBuilder.bookmarksScreen(
    lazyListState: LazyListState,
) {
    composable<BookmarksRoute> {
        BookmarkScreen(
            lazyListState = lazyListState,
            isTopAppBarVisible = true,
            /*modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)*/
        )
    }
}