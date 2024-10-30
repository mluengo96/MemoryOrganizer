package com.mluengo.memoryorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mluengo.memoryorganizer.navigation.NavigationActions
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.navigation.TopLevelDestination
import com.mluengo.memoryorganizer.navigation.bookmarks.bookmarksScreen
import com.mluengo.memoryorganizer.navigation.home.HomeRoute
import com.mluengo.memoryorganizer.navigation.home.folderListDetailScreen
import com.mluengo.memoryorganizer.navigation.home.homeScreen
import com.mluengo.memoryorganizer.navigation.settings.settingsScreen
import com.mluengo.memoryorganizer.ui.AppState
import com.mluengo.memoryorganizer.ui.components.NavigationBar
import com.mluengo.memoryorganizer.ui.components.fab.Fab
import com.mluengo.memoryorganizer.ui.rememberAppState
import com.mluengo.memoryorganizer.ui.screens.bookmarks.NewItemScreen
import com.mluengo.memoryorganizer.ui.screens.folders.NewFolderScreen
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryOrganizerTheme {
                val appState: AppState = rememberAppState()
                val navController = appState.navController
                val snackbarHostState = remember { SnackbarHostState() }
                val currentDestination = appState.currentTopLevelDestination
                val lazyListState = rememberLazyListState()
                val hapticFeedback = LocalHapticFeedback.current
                val navigationActions = remember(navController) {
                    NavigationActions(navController)
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        NavigationBar(
                            appState = appState,
                            navigateToTopLevelDestination = navigationActions::navigateToTopLevelDestination,
                        )
                    },
                    floatingActionButton = {
                        if (currentDestination != null) {
                            if (currentDestination.fabTitle != null) {
                                Fab(
                                    extended = lazyListState.isScrollingUp(),
                                    resourceId = currentDestination.fabTitle,
                                    onFabClick = {
                                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                        when (currentDestination) {
                                            TopLevelDestination.HOME -> navController.navigate(Route.NewFolderRoute)
                                            TopLevelDestination.BOOKMARKS -> navController.navigate(Route.NewItemRoute)
                                            TopLevelDestination.SETTINGS -> { }
                                        }
                                    },
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute(),
                        enterTransition = { slideInVertically { it / 16 } + fadeIn() },
                        exitTransition = { fadeOut(tween(0)) },
                        popEnterTransition = { slideInVertically { it / 16 } + fadeIn() },
                        popExitTransition = { fadeOut(tween(0)) },
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding),
                    ) {
                        homeScreen(lazyListState = lazyListState)
                        bookmarksScreen(lazyListState = lazyListState)
                        settingsScreen()
                        folderListDetailScreen(lazyListState = lazyListState)

                        composable<Route.NewFolderRoute> {
                            NewFolderScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = true,
                                onNavigateUp = navController::navigateUp
                            )
                        }

                        composable<Route.NewItemRoute> {
                            NewItemScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = true,
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class ContainerState {
    Collapsed,
    Expanded,
}

/**
 * Returns whether the lazy list is currently scrolling up.
 */
@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}