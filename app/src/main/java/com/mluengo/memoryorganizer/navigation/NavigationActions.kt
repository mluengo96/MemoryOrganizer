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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.mluengo.memoryorganizer.R
import kotlinx.serialization.Serializable

class NavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }
}

sealed interface Route {
    @Serializable data object Folders: Route
    @Serializable data object Bookmarks: Route
    @Serializable data object Settings: Route
    @Serializable data class Folder(val folderId: Int? = null): Route
    @Serializable data class Bookmark(val bookmarkId: String? = null): Route
    @Serializable data object NewFolder: Route
    @Serializable data object NewItem: Route
}

data class TopLevelDestination(
    val route: Route,
    @StringRes val resourceId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = Route.Folders,
        resourceId = R.string.folders_screen,
        selectedIcon = Icons.Rounded.Folder,
        unselectedIcon = Icons.Outlined.Folder
    ),
    TopLevelDestination(
        route = Route.Bookmarks,
        resourceId = R.string.bookmarks_screen,
        selectedIcon = Icons.Rounded.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks
    ),
    TopLevelDestination(
        route = Route.Settings,
        resourceId = R.string.settings_screen,
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings
    ),
)