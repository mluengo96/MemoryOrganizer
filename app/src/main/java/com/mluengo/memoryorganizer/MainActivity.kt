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
import com.mluengo.memoryorganizer.ui.AppState
import com.mluengo.memoryorganizer.ui.components.NavigationBar
import com.mluengo.memoryorganizer.ui.components.fab.Fab
import com.mluengo.memoryorganizer.ui.rememberAppState
import com.mluengo.memoryorganizer.ui.screens.bookmarks.BookmarkScreen
import com.mluengo.memoryorganizer.ui.screens.bookmarks.NewItemScreen
import com.mluengo.memoryorganizer.ui.screens.folders.FoldersOverviewScreen
import com.mluengo.memoryorganizer.ui.screens.folders.ItemScreen
import com.mluengo.memoryorganizer.ui.screens.folders.NewFolderScreen
import com.mluengo.memoryorganizer.ui.screens.settings.SettingsScreen
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
                            navigateToTopLevelDestination = navigationActions::navigateTo,
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
                                            TopLevelDestination.HOME -> navController.navigate(Route.NewFolder)
                                            TopLevelDestination.BOOKMAKRS -> navController.navigate(Route.NewItem)
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
                        startDestination = Route.Home,
                        enterTransition = { slideInVertically { it / 16 } + fadeIn() },
                        exitTransition = { fadeOut(tween(0)) },
                        popEnterTransition = { slideInVertically { it / 16 } + fadeIn() },
                        popExitTransition = { fadeOut(tween(0)) },
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding),
                    ) {
                        composable<Route.Home> {
                            FoldersOverviewScreen(
                                lazyListState = lazyListState,
                                onFolderClick = { folderId ->
                                    navController.navigate(
                                        Route.Folder(
                                            folderId = folderId
                                        )
                                    )
                                }
                            )
                        }
                        composable<Route.Bookmarks> {
                            BookmarkScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = true,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .consumeWindowInsets(innerPadding)
                            )
                        }
                        composable<Route.Settings> {
                            SettingsScreen(navController)
                        }
                        composable<Route.NewFolder> {
                            NewFolderScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = true,
                                onNavigateUp = navController::navigateUp
                            )
                        }
                        composable<Route.NewItem> {
                            NewItemScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = true,
                            )
                        }
                        composable<Route.Folder> {
                            ItemScreen(
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