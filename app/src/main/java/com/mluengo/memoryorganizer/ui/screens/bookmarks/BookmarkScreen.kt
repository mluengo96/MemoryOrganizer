package com.mluengo.memoryorganizer.ui.screens.bookmarks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.rounded.Archive
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.components.BookmarksTabIndicator
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    navController: NavController,
    lazyListState: LazyListState,
) {
    val spacing = LocalSpacing.current
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf(stringResource(id = R.string.bookmarks_tab), stringResource(id = R.string.archived_tab))

    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(0)  // Ensure the list always starts at the top when entering this screen
    }

    Column {
        PrimaryTabRow(
            selectedTabIndex = state,
            indicator = {
                BookmarksTabIndicator(Modifier.tabIndicatorOffset(state))
            },
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    icon = {
                        when (index) {
                            0 -> {
                                if (state != 0) {
                                    Icon(
                                        imageVector = Icons.Outlined.Bookmarks,
                                        contentDescription = null
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Rounded.Bookmarks,
                                        contentDescription = null
                                    )
                                }
                            }
                            1 -> {
                                if (state != 1) {
                                    Icon(
                                        imageVector = Icons.Outlined.Archive,
                                        contentDescription = null
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Rounded.Archive,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    text = { Text(
                        text = title,
                        style = MemoryOrganizerTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    ) },

                )
            }
        }
        when (state) {
            0 -> {
                BookmarksTab(
                    lazyListState = lazyListState
                )
            }
            1 -> {
                ArchivesTab()
            }
        }
    }

    /*if (state != 1) {
        Fab(
            text = "New Folder",
            onFabClick = { },
            modifier = Modifier
        )
    }*/
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun BookmarksScreenPreview() {
    BookmarkScreen(
        navController = rememberNavController(),
        lazyListState = rememberLazyListState()
    )
}