package com.mluengo.memoryorganizer.ui.screens.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.ui.components.ItemCard
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing

@Composable
fun BookmarksTab(
    lazyListState: LazyListState,
) {
    val spacing = LocalSpacing.current
    //EmptyBookmarksTab()
    LazyColumn(
        contentPadding = PaddingValues(spacing.spaceMedium),
        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall),
        state = lazyListState
    ) {
        // Add 5 items
        items(5) {
            ItemCard()
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun BookmarksTabPreview() {
    BookmarksTab(
        lazyListState = rememberLazyListState()
    )
}