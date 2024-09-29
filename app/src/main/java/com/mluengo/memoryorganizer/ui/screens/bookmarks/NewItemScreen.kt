package com.mluengo.memoryorganizer.ui.screens.bookmarks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.components.MenuButton
import com.mluengo.memoryorganizer.ui.components.TopAppBar
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemScreen(
    navController: NavController,
    lazyListState: LazyListState,
    isTopAppBarVisible: Boolean,
) {
    val spacing = LocalSpacing.current
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = stringResource(id = R.string.new_item),
            hasNavigationButton = false,
            actionIcon = Icons.Rounded.Close,
            actionIconContentDescription = stringResource(id = R.string.close),
            onActionClick = { navController.navigateUp() },
            lazyListState = lazyListState,
            isVisible = isTopAppBarVisible,
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = spacing.spaceMedium,
                        start = spacing.spaceMedium,
                        end = spacing.spaceMedium,
                        bottom = spacing.spaceLarge
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var titleText by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleText,
                    onValueChange = { titleText = it },
                    placeholder = { Text(stringResource(id = R.string.edit_text_title)) },
                    singleLine = true,
                    supportingText = { Text(stringResource(id = R.string.edit_text_required)) },
                    trailingIcon = {
                        AnimatedVisibility(visible = titleText.isNotBlank(), enter = fadeIn(), exit = fadeOut()) {
                            IconButton(onClick = { titleText = "" }) {
                                Icon(Icons.Rounded.Clear, "Clear")
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                var notesText by rememberSaveable { mutableStateOf("") }
                val maxDescriptionSize = 150
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    value = notesText,
                    onValueChange = { if (it.length <= maxDescriptionSize) notesText = it },
                    placeholder = { Text(stringResource(id = R.string.edit_text_notes)) },
                    singleLine = false,
                    supportingText = {
                        Row {
                            Spacer(Modifier.weight(1f))
                            Text("${notesText.count()}/$maxDescriptionSize")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                var linkText by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = linkText,
                    onValueChange = { linkText = it },
                    placeholder = { Text(stringResource(id = R.string.edit_text_http)) },
                    singleLine = true,
                    supportingText = { Text(stringResource(id = R.string.edit_text_required)) },
                    trailingIcon = {
                        AnimatedVisibility(visible = linkText.isNotBlank(), enter = fadeIn(), exit = fadeOut()) {
                            IconButton(onClick = { linkText = "" }) {
                                Icon(Icons.Rounded.Clear, "Clear")
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                var checked by remember { mutableStateOf(true) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(id = R.string.new_item_add_to_folder),
                        textAlign = TextAlign.Center,
                        style = MemoryOrganizerTypography.bodyLarge,
                    )

                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                }

                if (checked) {
                    Spacer(modifier = Modifier.height(spacing.spaceLarge))
                    MenuButton(
                        label = R.string.new_item_folder,
                        menuOptions = listOf(
                            "Note taking app",
                            "Jetpack Compose Resources",
                            "Figma tutorials",
                            "Blender Youtube Videos",
                            "Twitter/X important posts",
                            "Recipes",
                            "Test",
                            "Test but a test kinda long",
                            "Test but a test that is actually super long",
                        ),
                        onStatusClicked = { }
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = spacing.spaceMedium,
                    start = spacing.spaceMedium,
                    end = spacing.spaceMedium,
                    bottom = spacing.spaceLarge
                ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(id = R.string.new_item_add_button),
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun NNewItemScreenPreview() {
    NewItemScreen(
        navController = rememberNavController(),
        lazyListState = rememberLazyListState(),
        isTopAppBarVisible = true,
    )
}