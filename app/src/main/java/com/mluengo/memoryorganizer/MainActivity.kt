package com.mluengo.memoryorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.navigation.Screen
import com.mluengo.memoryorganizer.ui.AppState
import com.mluengo.memoryorganizer.ui.components.NavigationBar
import com.mluengo.memoryorganizer.ui.components.TopAppBar
import com.mluengo.memoryorganizer.ui.rememberAppState
import com.mluengo.memoryorganizer.ui.screens.bookmarks.BookmarkScreen
import com.mluengo.memoryorganizer.ui.screens.folders.FolderScreen
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

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        val currentBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = currentBackStackEntry?.destination?.route

                        when (currentDestination) {
                            Screen.Bookmarks.route -> { TopAppBar(titleRes = R.string.bookmarks_title) }
                            Route.NEW_FOLDER -> {
                                TopAppBar(
                                    titleRes = R.string.new_folder,
                                    hasNavigationButton = false,
                                    hasActionButton = true,
                                    actionIcon = Icons.Rounded.Close,
                                    actionIconContentDescription = stringResource(id = R.string.close),
                                    onActionClick = { navController.navigateUp() }
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
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Folders.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Folders.route) {
                            FolderScreen(navController)
                            appState.setShowBottomBar(true)
                        }
                        composable(Screen.Bookmarks.route) { BookmarkScreen(navController) }
                        composable(Screen.Settings.route) { SettingsScreen(navController) }
                        composable(Route.NEW_FOLDER) {
                            NewFolderScreen()
                            appState.setShowBottomBar(false)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoryOrganizerTheme {
        //Greeting("Android")
    }
}