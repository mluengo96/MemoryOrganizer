package com.mluengo.memoryorganizer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.core.navigation.TopLevelDestination
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.navigation.BookmarksRoute
import com.mluengo.memoryorganizer.core.navigation.HomeRoute
import com.mluengo.memoryorganizer.organizer.presentation.settings.navigation.SettingsRoute

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(
        navController,
    ) {
        AppState(
            navController = navController
        )
    }
}

@Stable
class AppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            with(currentDestination) {
                if (this?.hasRoute<HomeRoute>() == true) return TopLevelDestination.HOME
                if (this?.hasRoute<BookmarksRoute>() == true) return TopLevelDestination.BOOKMARKS
                if (this?.hasRoute<SettingsRoute>() == true) return TopLevelDestination.SETTINGS
            }
            return null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    var shouldShowBottomBar by mutableStateOf(true)
        private set

    fun setShowBottomBar(shouldShow: Boolean) {
        shouldShowBottomBar = shouldShow
    }
}