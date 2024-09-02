package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.navigation.Route
import com.mluengo.memoryorganizer.ui.components.Fab
import com.mluengo.memoryorganizer.ui.components.FolderCard
import com.mluengo.memoryorganizer.ui.components.HeaderFolders
import com.mluengo.memoryorganizer.ui.components.empty.EmptyFolderScreen
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun FolderScreen(
    navController: NavController,
) {
    val spacing = LocalSpacing.current
    if (false) {
        EmptyFolderScreen()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(spacing.spaceMedium),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        ) {
            item {
                HeaderFolders()
            }
            // Add 5 items
            items(5) {
                FolderCard()
            }
        }
    }

    Fab(
        text = stringResource(id = R.string.new_folder),
        onFabClick = {
            navController.navigate(Route.NEW_FOLDER)
        },
        modifier = Modifier
    )
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun FolderScreenPreview() {
    FolderScreen(navController = rememberNavController())
}