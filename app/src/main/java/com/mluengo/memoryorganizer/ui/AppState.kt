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
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.navigation.TopLevelDestination

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
                if (this?.hasRoute<Route.Home>() == true) return TopLevelDestination.HOME
                if (this?.hasRoute<Route.Bookmarks>() == true) return TopLevelDestination.BOOKMAKRS
                if (this?.hasRoute<Route.Settings>() == true) return TopLevelDestination.SETTINGS
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