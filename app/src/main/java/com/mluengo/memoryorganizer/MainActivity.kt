package com.mluengo.memoryorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.ui.AppState
import com.mluengo.memoryorganizer.ui.components.NavigationBar
import com.mluengo.memoryorganizer.ui.components.fab.Fab
import com.mluengo.memoryorganizer.ui.rememberAppState
import com.mluengo.memoryorganizer.ui.screens.bookmarks.BookmarkScreen
import com.mluengo.memoryorganizer.ui.screens.bookmarks.NewItemScreen
import com.mluengo.memoryorganizer.ui.screens.folders.FolderScreen
import com.mluengo.memoryorganizer.ui.screens.folders.ItemScreen
import com.mluengo.memoryorganizer.ui.screens.folders.NewFolderScreen
import com.mluengo.memoryorganizer.ui.screens.settings.SettingsScreen
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoryOrganizerTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val appState: AppState = rememberAppState()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination?.route
                val lazyListState = rememberLazyListState()

                // State of topBar, set state to false, if current page route is "car_details"
                val topBarState = rememberSaveable { (mutableStateOf(true)) }

                when (currentDestination) {
                    Route.FOLDERS -> {
                        appState.setShowBottomBar(true)
                        topBarState.value = false
                    }

                    Route.FOLDER -> {
                        appState.setShowBottomBar(false)
                        topBarState.value = true
                    }

                    Route.BOOKMARKS -> {
                        appState.setShowBottomBar(true)
                        topBarState.value = true
                    }

                    Route.NEW_FOLDER, Route.NEW_ITEM -> {
                        appState.setShowBottomBar(false)
                        topBarState.value = true
                    }
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    bottomBar = {
                        NavigationBar(
                            navController = navController,
                            shouldShowBottomBar = appState.shouldShowBottomBar,
                        )
                    },
                    floatingActionButton = {
                        when (currentDestination) {
                            Route.FOLDERS -> {
                                Fab(
                                    extended = lazyListState.isScrollingUp(),
                                    resourceId = R.string.new_folder,
                                    onFabClick = { navController.navigate(Route.NEW_FOLDER) },
                                    modifier = Modifier
                                )
                            }

                            Route.FOLDER, Route.BOOKMARKS -> {
                                Fab(
                                    extended = lazyListState.isScrollingUp(),
                                    resourceId = R.string.new_item,
                                    onFabClick = { navController.navigate(Route.NEW_ITEM) },
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.FOLDERS,
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                    ) {
                        composable(
                            route = Route.FOLDERS,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            },
                        ) {
                            FolderScreen(
                                navController = navController,
                                lazyListState = lazyListState
                            )
                        }
                        composable(
                            route = Route.BOOKMARKS,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            },
                        ) {
                            BookmarkScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = topBarState.value,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .consumeWindowInsets(innerPadding)
                            )
                        }
                        composable(
                            route = Route.SETTINGS,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            },
                        ) {
                            SettingsScreen(navController)
                        }
                        composable(
                            route = Route.NEW_FOLDER,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down
                                )
                            },
                        ) {
                            NewFolderScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = topBarState.value,
                            )

                        }
                        composable(
                            route = Route.NEW_ITEM,
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(300, easing = LinearEasing)
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down
                                )
                            },
                        ) {
                            NewItemScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = topBarState.value,
                            )
                        }
                        composable(Route.FOLDER) {
                            ItemScreen(
                                navController = navController,
                                lazyListState = lazyListState,
                                isTopAppBarVisible = topBarState.value
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