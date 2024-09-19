package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.components.cards.ItemCard
import com.mluengo.memoryorganizer.ui.components.TopAppBar
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun ItemScreen(
    navController: NavController,
    lazyListState: LazyListState,
    isTopAppBarVisible: Boolean,
) {
    val spacing = LocalSpacing.current
    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(0)  // Ensure the list always starts at the top when entering this screen
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        TopAppBar(
            title = "Folder's name",
            hasNavigationButton = true,
            actionIcon = Icons.Rounded.MoreVert,
            actionIconContentDescription = stringResource(id = R.string.edit),
            onActionClick = { /* TODO */ },
            navigationIconContentDescription = stringResource(id = R.string.back),
            onNavigationClick = { navController.navigateUp() },
            lazyListState = lazyListState,
            isVisible = isTopAppBarVisible,
        )
        Column {
            LazyColumn(
                contentPadding = PaddingValues(spacing.spaceMedium),
                verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
                state = lazyListState
            ) {
                item {
                    Text(
                        text = "Lorem ipsum dolor sit amet consectetur adipiscing elit montes, interdum odio massa ultricies feugiat penatibus per dignissim, gravida urna mus neque consequat orci congue. Hac aptent inceptos lacinia rhoncus torquent donec etiam maecenas vulputate egestas, pharetra rutrum accumsan libero commodo imperdiet iaculis sociosqu turpis. Himenaeos duis habitasse lobortis, ligula conubia.",
                        textAlign = TextAlign.Start,
                        style = MemoryOrganizerTypography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceLarge))
                }

                val testLinks = listOf(
                    "https://not-valid-url", // --> Invalid URL
                    "https://m3.material.io/develop/android/jetpack-compose", // --> Valid URL
                    "https://expatexplore.com/blog/when-to-travel-weather-seasons/", // --> URL that does not contain image
                )

                // Add 5 items
                items(testLinks) { link ->
                    ItemCard(
                        title = "",
                        description = "",
                        imageUrl = ""
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun ItemScreenPreview() {
    ItemScreen(
        navController = rememberNavController(),
        lazyListState = rememberLazyListState(),
        isTopAppBarVisible = true
    )
}