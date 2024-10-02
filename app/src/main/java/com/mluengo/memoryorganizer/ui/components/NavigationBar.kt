package com.mluengo.memoryorganizer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.navigation.TOP_LEVEL_DESTINATIONS
import com.mluengo.memoryorganizer.navigation.TopLevelDestination

@Composable
fun NavigationBar(
    navController: NavHostController,
    shouldShowBottomBar: Boolean,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

   AnimatedVisibility(
       visible = shouldShowBottomBar,
       enter = slideInVertically(initialOffsetY = { it }),
       exit = slideOutVertically(targetOffsetY = { it })
   ) {
       NavigationBar {
           TOP_LEVEL_DESTINATIONS.forEach { screen ->
               NavigationBarItem(
                   icon = {
                       if (currentDestination.hasRoute(screen))
                           Icon(imageVector = screen.selectedIcon, contentDescription = stringResource(id = screen.resourceId))
                       else
                           Icon(imageVector = screen.unselectedIcon, contentDescription = stringResource(id = screen.resourceId))
                   },
                   label = { Text(text = stringResource(id = screen.resourceId)) },
                   selected = currentDestination.hasRoute(screen),
                   onClick = { navigateToTopLevelDestination(screen) }
               )
           }
       }
   }
}

fun NavDestination?.hasRoute(destination: TopLevelDestination): Boolean =
    this?.hasRoute(destination.route::class) ?: false

@Preview()
@Composable
fun NavigationBarPreview(

) {
    NavigationBar(
        navController = rememberNavController(),
        shouldShowBottomBar = true,
        navigateToTopLevelDestination = { }
    )
}