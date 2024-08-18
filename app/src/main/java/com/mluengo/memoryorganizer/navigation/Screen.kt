package com.mluengo.memoryorganizer.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.mluengo.memoryorganizer.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    data object Folders: Screen("folder", R.string.folders_screen, Icons.Rounded.Folder, Icons.Outlined.Folder)
    data object Bookmarks: Screen("bookmarks", R.string.bookmarks_screen, Icons.Rounded.Bookmarks, Icons.Outlined.Bookmarks)
    data object Settings: Screen("settings", R.string.settings_screen, Icons.Rounded.Settings, Icons.Outlined.Settings)
}