package com.mluengo.memoryorganizer.core.navigation

import androidx.annotation.Keep
import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.memoryorganizer.core.presentation.FolderListDetailScreen
import kotlinx.serialization.Serializable

@Serializable data class HomeRoute(
    // The ID of the folder which will be initially selected at this destination
    val initialFolderId: String? = null,
)

@Serializable data object FolderPlaceholderRoute

// TODO: Remove @Keep when https://issuetracker.google.com/353898971 is fixed
@Keep
@Serializable data object DetailPaneNavHostRoute

fun NavController.navigateToHome(
    initialFolderId: String? = null,
    navOptions: NavOptions? = null,
) = navigate(route = HomeRoute(initialFolderId), navOptions)

fun NavGraphBuilder.homeScreen(
    lazyListState: LazyListState,
) {
    composable<HomeRoute> {
        FolderListDetailScreen(
            lazyListState = lazyListState,
        )
    }
}