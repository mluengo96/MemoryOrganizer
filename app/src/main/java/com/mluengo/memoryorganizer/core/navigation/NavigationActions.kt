package com.mluengo.memoryorganizer.core.navigation

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
import androidx.navigation.navOptions
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.core.navigation.HomeRoute
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.navigation.BookmarksRoute
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.navigation.navigateToBookmarks
import com.mluengo.memoryorganizer.organizer.presentation.settings.navigation.SettingsRoute
import com.mluengo.memoryorganizer.organizer.presentation.settings.navigation.navigateToSettings
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

class NavigationActions(private val navController: NavHostController) {
    fun navigateToTopLevelDestination(destination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
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

        when (destination) {
            TopLevelDestination.HOME -> navController.navigateToHome(navOptions = topLevelNavOptions)
            TopLevelDestination.BOOKMARKS -> navController.navigateToBookmarks(navOptions = topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettings(navOptions = topLevelNavOptions)
        }
    }
}

sealed interface Route {
    @Serializable data class BookmarkRoute(val bookmarkId: String? = null): Route
    @Serializable data object NewFolderRoute: Route
    @Serializable data object NewItemRoute: Route
}

enum class TopLevelDestination(
    val route: KClass<*>,
    @StringRes val resourceId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val fabTitle: Int?,
) {
    HOME(
        route = HomeRoute::class,
        resourceId = R.string.folders_screen,
        selectedIcon = Icons.Rounded.Folder,
        unselectedIcon = Icons.Outlined.Folder,
        fabTitle = R.string.new_folder
    ),
    BOOKMARKS(
        route = BookmarksRoute::class,
        resourceId = R.string.bookmarks_screen,
        selectedIcon = Icons.Rounded.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks,
        fabTitle = R.string.new_item
    ),
    SETTINGS(
        route = SettingsRoute::class,
        resourceId = R.string.settings_screen,
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        fabTitle = null
    ),
}