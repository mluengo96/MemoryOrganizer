package com.mluengo.memoryorganizer.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    @StringRes titleRes: Int,
    hasNavigationButton: Boolean = false,
    hasActionButton: Boolean = false,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String? = null,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    onNavigationClick: () -> Unit = { },
    onActionClick: () -> Unit = { },
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MemoryOrganizerTypography.titleLarge
            )
        },
        navigationIcon = {
            if (hasNavigationButton) {
                IconButton(onClick = onNavigationClick) {
                    if (navigationIcon != null) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = navigationIconContentDescription,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior,
        actions = {
            if (hasActionButton) {
                IconButton(onClick = onActionClick) {
                    if (actionIcon != null) {
                        Icon(
                            imageVector = actionIcon,
                            contentDescription = actionIconContentDescription
                        )
                    }
                }
            }
        },
    )
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun CenterAppBarPreview() {
    TopAppBar(
        titleRes = android.R.string.untitled,
        navigationIcon = Icons.Rounded.Search,
        navigationIconContentDescription = "Navigation icon",
        actionIcon = Icons.Rounded.MoreVert,
        actionIconContentDescription = "Action icon",
    )
}