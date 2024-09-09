package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.ui.components.ItemCard
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun ItemScreen(
    navController: NavController,
    lazyListState: LazyListState,
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

                // Add 5 items
                items(5) {
                    ItemCard()
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
        lazyListState = rememberLazyListState()
    )
}