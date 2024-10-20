package com.mluengo.memoryorganizer.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mluengo.memoryorganizer.navigation.TopLevelDestination
import com.mluengo.memoryorganizer.ui.AppState
import com.mluengo.memoryorganizer.ui.rememberAppState
import kotlin.reflect.KClass

@Composable
fun NavigationBar(
    appState: AppState,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit,
) {
    val topLevelDestinations = appState.topLevelDestinations
    val currentDestination = appState.currentDestination

    NavigationBar {
        topLevelDestinations.forEach { screen ->
            val selected = currentDestination.isRouteInHierarchy(screen.route)
            if (currentDestination != null) {
                NavigationBarItem(
                    icon = {
                        if (selected)
                            Icon(imageVector = screen.selectedIcon, contentDescription = stringResource(id = screen.resourceId))
                        else
                            Icon(imageVector = screen.unselectedIcon, contentDescription = stringResource(id = screen.resourceId))
                    },
                    label = { Text(text = stringResource(id = screen.resourceId)) },
                    selected = selected,
                    onClick = { navigateToTopLevelDestination(screen) }
                )
            }
        }
    }
}

fun NavDestination?.isRouteInHierarchy(route: KClass<*>): Boolean =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

@Preview
@Composable
fun NavigationBarPreview(

) {
    NavigationBar(
        appState = rememberAppState(),
        navigateToTopLevelDestination = { }
    )
}