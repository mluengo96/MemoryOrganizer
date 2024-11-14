package com.mluengo.memoryorganizer.organizer.presentation.folder_detail.navigation

import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.FolderDetailScreen
import kotlinx.serialization.Serializable

@Serializable data class FolderRoute(val id: String)

fun NavController.navigateToFolder(folderId: String, navOptions: NavOptionsBuilder.() -> Unit = { }) {
    navigate(route = FolderRoute(folderId)) {
        navOptions()
    }
}

fun NavGraphBuilder.folderScreen(
    lazyListState: LazyListState,
    onNavigateUp: () -> Unit,
) {
    composable<FolderRoute> {
        FolderDetailScreen(
            lazyListState = lazyListState,
            isTopAppBarVisible = true,
            onNavigateUp = onNavigateUp
        )
    }
}