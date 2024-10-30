package com.mluengo.memoryorganizer.navigation.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mluengo.memoryorganizer.ui.screens.folders.FolderListDetailScreen
import kotlinx.serialization.Serializable

@Serializable data class HomeRoute(
    // The ID of the folder which will be initially selected at this destination
    val initialFolderId: String? = null,
)

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