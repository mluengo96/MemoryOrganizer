package com.mluengo.memoryorganizer.navigation.home

import androidx.annotation.Keep
import androidx.compose.foundation.lazy.LazyListState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mluengo.memoryorganizer.navigation.folder.FolderRoute
import com.mluengo.memoryorganizer.ui.screens.folders.FolderListDetailScreen
import kotlinx.serialization.Serializable

@Serializable data object FolderPlaceholderRoute

// TODO: Remove @Keep when https://issuetracker.google.com/353898971 is fixed
@Keep
@Serializable data object DetailPaneNavHostRoute

fun NavGraphBuilder.folderListDetailScreen(
    lazyListState: LazyListState
) {
    composable<FolderRoute> {
        FolderListDetailScreen(
            lazyListState = lazyListState
        )
    }
}