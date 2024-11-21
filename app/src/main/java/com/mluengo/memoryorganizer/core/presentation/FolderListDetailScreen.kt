package com.mluengo.memoryorganizer.core.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldDestinationItem
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.core.navigation.DetailPaneNavHostRoute
import com.mluengo.memoryorganizer.core.navigation.FolderPlaceholderRoute
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.navigation.FolderRoute
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.navigation.folderScreen
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.navigation.navigateToFolder
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.FoldersOverviewScreen
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
internal fun FolderListDetailScreen(
    lazyGridState: LazyGridState,
    lazyListState: LazyListState,
    viewModel: Folders2PaneViewModel = koinViewModel(),
) {
    val selectedFolderId by viewModel.selectedFolderId.collectAsStateWithLifecycle()
    FolderListDetailScreen(
        selectedFolderId = selectedFolderId,
        lazyGridState = lazyGridState,
        lazyListState = lazyListState,
        onFolderClick = viewModel::onFolderClick,
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
internal fun FolderListDetailScreen(
    selectedFolderId: String?,
    lazyGridState: LazyGridState,
    lazyListState: LazyListState,
    onFolderClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listDetailNavigator = rememberListDetailPaneScaffoldNavigator<Any>(
        initialDestinationHistory = listOfNotNull(
            ThreePaneScaffoldDestinationItem(ListDetailPaneScaffoldRole.List),
            ThreePaneScaffoldDestinationItem<Nothing>(ListDetailPaneScaffoldRole.Detail).takeIf {
                selectedFolderId != null
            },
        ),
    )
    BackHandler(listDetailNavigator.canNavigateBack()) {
        listDetailNavigator.navigateBack()
    }

    var nestedNavHostStartRoute by remember {
        val route = selectedFolderId?.let { FolderRoute(id = it) } ?: FolderPlaceholderRoute
        mutableStateOf(route)
    }
    var nestedNavKey by rememberSaveable(
        stateSaver = Saver({ it.toString() }, UUID::fromString),
    ) {
        mutableStateOf(UUID.randomUUID())
    }
    val nestedNavController = key(nestedNavKey) {
        rememberNavController()
    }

    fun onFolderClickShowDetailPane(folderId: String) {
        onFolderClick(folderId)
        if (listDetailNavigator.isDetailPaneVisible()) {
            // If the detail pane was visible, then use the nestedNavController navigate call directly
            nestedNavController.navigateToFolder(folderId) {
                popUpTo<DetailPaneNavHostRoute>()
            }
        } else {
            // Otherwise, recreate the NavHost entirely, and start at the new destination
            nestedNavHostStartRoute = FolderRoute(id = folderId)
            nestedNavKey = UUID.randomUUID()
        }
        listDetailNavigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
    }

    ListDetailPaneScaffold(
        directive = listDetailNavigator.scaffoldDirective,
        value = listDetailNavigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                FoldersOverviewScreen(
                    lazyGridState = lazyGridState,
                    onFolderClick = ::onFolderClickShowDetailPane
                )
            }
        },
        detailPane = {
            // Hide FAB
            val isDetailVisible =
                listDetailNavigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail] == PaneAdaptedValue.Expanded
            AnimatedPane {
                key(nestedNavKey) {
                    NavHost(
                        navController = nestedNavController,
                        startDestination = nestedNavHostStartRoute,
                        route = DetailPaneNavHostRoute::class
                    ) {
                        folderScreen(
                            lazyGridState = lazyGridState,
                            onNavigateUp = listDetailNavigator::navigateBack
                        )
                        composable<FolderPlaceholderRoute> {
                            //FolderDetailPlaceholder()
                        }
                    }
                }
            }
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun <T> ThreePaneScaffoldNavigator<T>.isListPaneVisible(): Boolean =
    scaffoldValue[ListDetailPaneScaffoldRole.List] == PaneAdaptedValue.Expanded

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
private fun <T> ThreePaneScaffoldNavigator<T>.isDetailPaneVisible(): Boolean =
    scaffoldValue[ListDetailPaneScaffoldRole.Detail] == PaneAdaptedValue.Expanded