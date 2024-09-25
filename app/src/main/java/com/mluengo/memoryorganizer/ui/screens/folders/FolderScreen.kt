package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.ui.components.HeaderFolders
import com.mluengo.memoryorganizer.ui.components.cards.FolderCard
import com.mluengo.memoryorganizer.ui.components.empty.EmptyFolderScreen
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun FolderScreen(
    navController: NavController,
    lazyListState: LazyListState,
) {
    val spacing = LocalSpacing.current

    // Ensure the list always starts at the top when entering this screen
    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(0)
    }

    if (true) {
        EmptyFolderScreen()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
            state = lazyListState
        ) {
            item {
                HeaderFolders()
            }

            // Add 5 items
            items(5) {
                FolderCard(
                    onClick = { navController.navigate(Route.FOLDER) },
                    title = "Note taking app"
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun FolderScreenPreview() {
    FolderScreen(
        navController = rememberNavController(),
        lazyListState = rememberLazyListState()
    )
}