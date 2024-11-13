package com.mluengo.memoryorganizer.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.Shapes
import com.mluengo.memoryorganizer.ui.theme.outlineLight
import com.mluengo.memoryorganizer.util.FolderStatus

@Composable
fun MenuButton(
    @StringRes label: Int,
    menuOptions: List<String>,
    onStatusClicked: (String) -> Unit,
) {
    val spacing = LocalSpacing.current
    val density = LocalDensity.current

    var isContextMenuVisible by remember { mutableStateOf(false) }
    var pressOffset by remember { mutableStateOf(DpOffset.Zero) }
    var itemHeight by remember { mutableStateOf(0.dp) }
    var itemWidth by remember { mutableStateOf(0.dp) }
    var statusText by rememberSaveable { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)
    ) {
        OutlinedCard(
            modifier = Modifier
                .onSizeChanged {
                    itemHeight = with(density) { it.height.toDp() }
                    itemWidth = with(density) { it.width.toDp() }
                }
                .fillMaxWidth(.5f)
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
                val degrees by animateFloatAsState(
                    if (isContextMenuVisible) -90f else 90f,
                    label = "Icon rotation"
                )
                if (statusText.isBlank()) {
                    Text(text = stringResource(id = label))
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

        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = DpOffset(itemWidth, itemHeight),
            modifier = Modifier.fillMaxWidth(.45f),
        ) {
            menuOptions.forEach { option: String ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        statusText = option
                        onStatusClicked(option)
                        isContextMenuVisible = false
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun MenuButtonPreview() {
    MenuButton(
        R.string.new_folders_status,
        listOf(
            stringResource(id = FolderStatus.TODO.statusResId),
            stringResource(id = FolderStatus.IN_PROGRESS.statusResId),
            stringResource(id = FolderStatus.DONE.statusResId)
        ),
        onStatusClicked = { },
    )
}