package com.mluengo.memoryorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.MoreVert
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
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.navigation.Screen
import com.mluengo.memoryorganizer.ui.AppState
import com.mluengo.memoryorganizer.ui.components.Fab
import com.mluengo.memoryorganizer.ui.components.NavigationBar
import com.mluengo.memoryorganizer.ui.components.TopAppBar
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

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        when (currentDestination) {
                            Screen.Bookmarks.route -> { TopAppBar(title = stringResource(id = R.string.bookmarks_title)) }
                            Route.NEW_FOLDER -> {
                                TopAppBar(
                                    title = stringResource(id = R.string.new_folder),
                                    hasNavigationButton = false,
                                    hasActionButton = true,
                                    actionIcon = Icons.Rounded.Close,
                                    actionIconContentDescription = stringResource(id = R.string.close),
                                    onActionClick = { navController.navigateUp() }
                                )
                            }
                            Route.FOLDER -> {
                                TopAppBar(
                                    title = "Folder's name",
                                    hasNavigationButton = true,
                                    hasActionButton = true,
                                    actionIcon = Icons.Rounded.MoreVert,
                                    actionIconContentDescription = stringResource(id = R.string.edit),
                                    onActionClick = { /* TODO */ },
                                    navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                                    navigationIconContentDescription = stringResource(id = R.string.back),
                                    onNavigationClick = { navController.navigateUp() },
                                )
                            }
                        }
                    },
                    bottomBar = {
                        if (appState.shouldShowBottomBar) {
                            NavigationBar(
                                navController = navController
                            )
                        }
                    },
                    floatingActionButton = {
                        when (currentDestination) {
                            Screen.Folders.route -> {
                                Fab(
                                    extended = lazyListState.isScrollingUp(),
                                    resourceId = R.string.new_folder,
                                    onFabClick = {
                                        navController.navigate(Route.NEW_FOLDER)
                                    },
                                    modifier = Modifier
                                )
                            }
                            Screen.Bookmarks.route, Route.FOLDER -> {
                                Fab(
                                    extended = lazyListState.isScrollingUp(),
                                    resourceId = R.string.new_item,
                                    onFabClick = {
                                        navController.navigate(Route.NEW_ITEM)
                                    },
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Folders.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Folders.route) {
                            FolderScreen(
                                navController = navController,
                                lazyListState = lazyListState
                            )
                            appState.setShowBottomBar(true)
                        }
                        composable(Screen.Bookmarks.route) { BookmarkScreen(navController, lazyListState) }
                        composable(Screen.Settings.route) { SettingsScreen(navController) }
                        composable(Route.NEW_FOLDER) {
                            NewFolderScreen()
                            appState.setShowBottomBar(false)
                        }
                        composable(Route.NEW_ITEM) {
                            NewItemScreen()
                            appState.setShowBottomBar(false)
                        }
                        composable(Route.FOLDER) {
                            ItemScreen(
                                navController = navController,
                                lazyListState = lazyListState
                            )
                            appState.setShowBottomBar(false)
                        }
                    }
                }
            }
        }
    }
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