package com.mluengo.memoryorganizer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    hasNavigationButton: Boolean = false,
    navigationIconContentDescription: String? = null,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    onNavigationClick: () -> Unit = { },
    onActionClick: () -> Unit = { },
    lazyListState: LazyListState,
    isVisible: Boolean,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val coroutineScope = rememberCoroutineScope()
    val hapticFeedback = LocalHapticFeedback.current

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    lazyListState.animateScrollToItem(0)
                }
            },
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MemoryOrganizerTypography.titleLarge
                )
            },
            navigationIcon = {
                if (hasNavigationButton) {
                    IconButton(
                        onClick = {
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            onNavigationClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = navigationIconContentDescription,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            },
            //scrollBehavior = scrollBehavior,
            actions = {
                if (actionIcon != null) {
                    IconButton(
                        onClick = {
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                            onActionClick()
                        }
                    ) {
                        Icon(
                            imageVector = actionIcon,
                            contentDescription = actionIconContentDescription
                        )
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun CenterAppBarPreview() {
    TopAppBar(
        title = stringResource(id = android.R.string.untitled),
        navigationIconContentDescription = "Navigation icon",
        actionIcon = Icons.Rounded.MoreVert,
        actionIconContentDescription = "Action icon",
        lazyListState = rememberLazyListState(),
        isVisible = true,
    )
}