package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EmojiEmotions
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.emoji2.emojipicker.EmojiPickerView
import androidx.emoji2.emojipicker.EmojiViewItem
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography
import com.mluengo.memoryorganizer.ui.theme.Shapes
import com.mluengo.memoryorganizer.ui.theme.outlineLight

@Composable
fun NewFolderScreen(

) {
    val spacing = LocalSpacing.current
    var showBottomSheet by remember { mutableStateOf(false) }
    var emojiViewItem by remember {
        mutableStateOf(EmojiViewItem("", emptyList()))
    }

    Column(
        Modifier
            .fillMaxHeight()
            .padding(
                top = spacing.spaceMedium,
                start = spacing.spaceMedium,
                end = spacing.spaceMedium,
                bottom = spacing.spaceLarge
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f, false)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium),
            ) {
                OutlinedIconButton(onClick = { showBottomSheet = true }, modifier = Modifier.size(56.dp), shape = CircleShape, border = BorderStroke(1.dp, outlineLight)) {
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

                var titleText by rememberSaveable { mutableStateOf("") }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleText,
                    onValueChange = { titleText = it },
                    placeholder = { Text(stringResource(id = R.string.new_folders_title)) },
                    singleLine = true,
                    supportingText = { Text(stringResource(id = R.string.new_folders_required)) }
                )

            }
            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            var descriptionText by rememberSaveable { mutableStateOf("") }
            val maxDescriptionSize = 150
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                value = descriptionText,
                onValueChange = { if (it.length <= maxDescriptionSize) descriptionText = it },
                placeholder = { Text(stringResource(id = R.string.new_folders_description)) },
                singleLine = false,
                supportingText = {
                    Row {
                        Spacer(Modifier.weight(1f))
                        Text("${descriptionText.count()}/$maxDescriptionSize")
                    }
                }
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

                var isContextMenuVisible by remember { mutableStateOf(false) }
                var pressOffset by remember {
                    mutableStateOf(DpOffset.Zero)
                }
                var itemHeight by remember {
                    mutableStateOf(0.dp)
                }

                var statusText by rememberSaveable { mutableStateOf("") }
                val density = LocalDensity.current

                Box(modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
                ) {
                    OutlinedCard(
                        modifier = Modifier
                            .onSizeChanged {
                                itemHeight = with(density) { it.height.toDp() }
                            }
                            .width(200.dp)
                            .height(56.dp)
                            .pointerInput(true) {
                                detectTapGestures(
                                    onTap = {
                                        isContextMenuVisible = true
                                        pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                                    }
                                )
                            },
                        shape = Shapes.extraSmall,
                        border = BorderStroke(1.dp, outlineLight),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spacing.spaceMedium),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val degrees by animateFloatAsState(if (isContextMenuVisible) -90f else 90f)
                            if (statusText.isEmpty()) {
                                Text(text = stringResource(id = R.string.new_folders_status))
                            } else {
                                Text(text = statusText)
                            }

                            Icon(
                                imageVector = Icons.Rounded.ChevronRight,
                                contentDescription = "Open Status menu",
                                modifier = Modifier.rotate(degrees)
                            )
                        }
                    }

                    val todoText = stringResource(id = R.string.new_folders_status_todo)
                    val inProgressText = stringResource(id = R.string.new_folders_status_in_progress)
                    val doneText = stringResource(id = R.string.new_folders_status_done)

                    DropdownMenu(
                        expanded = isContextMenuVisible,
                        onDismissRequest = { isContextMenuVisible = false },
                        modifier = Modifier.width(200.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(todoText) },
                            onClick = {
                                statusText = todoText
                                isContextMenuVisible = false
                            },
                        )
                        DropdownMenuItem(
                            text = { Text(inProgressText) },
                            onClick = {
                                statusText = inProgressText
                                isContextMenuVisible = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(doneText) },
                            onClick = {
                                statusText = doneText
                                isContextMenuVisible = false
                            },
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(id = R.string.new_folders_create),
            )
        }

        if (showBottomSheet) {
            Dialog(
                onDismissRequest = { showBottomSheet = false }
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
                                        showBottomSheet = false
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
    NewFolderScreen()
}