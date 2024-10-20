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
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.mluengo.memoryorganizer.R
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

class NavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: TopLevelDestination) {
        val navOptions = navOptions {
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
            TopLevelDestination.HOME -> navController.navigateToHome(navOptions = navOptions)
            TopLevelDestination.BOOKMAKRS -> navController.navigateToBookmarks(navOptions = navOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettings(navOptions = navOptions)
        }
    }
}

sealed interface Route {
    @Serializable data object Home: Route
    @Serializable data object Bookmarks: Route
    @Serializable data object Settings: Route
    @Serializable data class Folder(val folderId: Int? = null): Route
    @Serializable data class Bookmark(val bookmarkId: String? = null): Route
    @Serializable data object NewFolder: Route
    @Serializable data object NewItem: Route
}

enum class TopLevelDestination(
    val route: KClass<*>,
    @StringRes val resourceId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val fabTitle: Int?,
) {
    HOME(
        route = Route.Home::class,
        resourceId = R.string.folders_screen,
        selectedIcon = Icons.Rounded.Folder,
        unselectedIcon = Icons.Outlined.Folder,
        fabTitle = R.string.new_folder
    ),
    BOOKMAKRS(
        route = Route.Bookmarks::class,
        resourceId = R.string.bookmarks_screen,
        selectedIcon = Icons.Rounded.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks,
        fabTitle = R.string.new_item
    ),
    SETTINGS(
        route = Route.Settings::class,
        resourceId = R.string.settings_screen,
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        fabTitle = null
    ),
}

fun NavController.navigateToHome(
    navOptions: NavOptions? = null,
) = navigate(
    route = Route.Home,
    navOptions = navOptions
)

fun NavController.navigateToBookmarks(
    navOptions: NavOptions? = null,
) = navigate(
    route = Route.Bookmarks,
    navOptions = navOptions
)

fun NavController.navigateToSettings(
    navOptions: NavOptions? = null,
) = navigate(
    route = Route.Settings,
    navOptions = navOptions
)