package com.mluengo.memoryorganizer.organizer.presentation.new_folder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.emoji2.emojipicker.EmojiPickerView
import androidx.emoji2.emojipicker.EmojiViewItem
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.core.presentation.components.MenuButton
import com.mluengo.memoryorganizer.core.presentation.components.TopAppBar
import com.mluengo.memoryorganizer.core.presentation.util.FolderStatus
import com.mluengo.memoryorganizer.core.presentation.util.UiEvent
import com.mluengo.memoryorganizer.organizer.domain.model.Folder
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.FolderEvent
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import com.mluengo.memoryorganizer.ui.theme.Shapes
import com.mluengo.memoryorganizer.ui.theme.outlineLight
import org.koin.androidx.compose.koinViewModel
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Composable
fun NewFolderScreen(
    navController: NavController,
    lazyListState: LazyListState,
    isTopAppBarVisible: Boolean,
    onNavigateUp: () -> Unit,
    viewModel: NewFolderViewModel = koinViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val hapticFeedback = LocalHapticFeedback.current
    val focusManager = LocalFocusManager.current

    var showDialog by remember { mutableStateOf(false) }
    var emojiViewItem by remember {
        mutableStateOf(EmojiViewItem("", emptyList()))
    }

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                /*is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }*/
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = stringResource(id = R.string.new_folder),
            hasNavigationButton = false,
            actionIcon = Icons.Rounded.Close,
            actionIconContentDescription = stringResource(id = R.string.close),
            onActionClick = { navController.navigateUp() },
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
                        bottom = spacing.spaceLarge,
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
                ) {
                    OutlinedIconButton(onClick = { showDialog = true }, modifier = Modifier.size(56.dp), shape = CircleShape, border = BorderStroke(1.dp, outlineLight)) {
                        if (emojiViewItem.emoji.isEmpty()) {
                            Icon(
                                imageVector = Icons.Outlined.EmojiEmotions,
                                contentDescription = "",
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Text(text = emojiViewItem.emoji)
                        }
                    }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.title,
                        onValueChange = viewModel::onTitleAdded,
                        placeholder = { Text(stringResource(id = R.string.edit_text_title)) },
                        singleLine = true,
                        supportingText = { Text(stringResource(id = R.string.edit_text_required)) },
                        isError = viewModel.titleIsEmpty,
                        trailingIcon = {
                            AnimatedVisibility(visible = state.title.isNotBlank(), enter = fadeIn(), exit = fadeOut()) {
                                IconButton(onClick = viewModel::onTitleCleared) {
                                    Icon(Icons.Rounded.Clear, "Clear")
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, capitalization = KeyboardCapitalization.Sentences),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                val maxDescriptionSize = 150
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    value = state.description,
                    onValueChange = {
                        if (it.length <= maxDescriptionSize)
                            viewModel.onDescriptionAdded(folderDescription = it)
                    },
                    placeholder = { Text(stringResource(id = R.string.edit_text_description)) },
                    singleLine = false,
                    supportingText = {
                        Row {
                            Spacer(Modifier.weight(1f))
                            Text("${state.description.count()}/$maxDescriptionSize")
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, capitalization = KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                var checked by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(id = R.string.new_folders_set_status),
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
                        label = R.string.new_folders_status,
                        menuOptions = listOf(
                            stringResource(id = FolderStatus.TODO.statusResId),
                            stringResource(id = FolderStatus.IN_PROGRESS.statusResId),
                            stringResource(id = FolderStatus.DONE.statusResId)
                        ),
                        onStatusClicked = viewModel::onStatusSelected
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    top = spacing.spaceMedium,
                    start = spacing.spaceMedium,
                    end = spacing.spaceMedium,
                    bottom = spacing.spaceLarge
                ),
            onClick = {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                viewModel.onEvent(
                    FolderEvent.OnCreateFolderClick(
                        Folder(
                            id = state.id,
                            title = state.title,
                            description = state.description,
                            status = state.status,
                            iconResId = state.iconResId,
                            itemList = state.itemList
                        )
                    )
                )
            },
            enabled = !viewModel.titleIsEmpty
        ) {
            Text(
                text = stringResource(id = R.string.new_folders_create),
            )
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false }
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(375.dp),
                    shape = Shapes.large,
                ) {
                    AndroidView(
                        factory = { context ->
                            EmojiPickerView(context)
                                .apply {
                                    // setting row $columns - Optional
                                    emojiGridColumns = 9
                                    emojiGridRows = 6f
                                    // set pick listener
                                    setOnEmojiPickedListener { item ->
                                        emojiViewItem = item
                                        showDialog = false
                                    }
                                }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = spacing.spaceSmall,
                                bottom = spacing.default,
                                start = spacing.spaceSmall,
                                end = spacing.spaceSmall
                            ),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun NewFolderScreenPreview() {
    NewFolderScreen(
        navController = rememberNavController(),
        lazyListState = rememberLazyListState(),
        isTopAppBarVisible = true,
        onNavigateUp = { }
    )
}