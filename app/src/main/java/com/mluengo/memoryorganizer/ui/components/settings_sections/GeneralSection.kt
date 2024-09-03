package com.mluengo.memoryorganizer.ui.components.settings_sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.components.icons.App_badging
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun GeneralSection(

) {
    val spacing = LocalSpacing.current

    Column {
        Text(
            text = stringResource(id = R.string.general_title),
            textAlign = TextAlign.Start,
            style = MemoryOrganizerTypography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        ListItem(
            headlineContent = { Text(stringResource(id = R.string.app_icon)) },
            leadingContent = {
                Icon(
                    App_badging,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = {
                Icon(
                    Icons.AutoMirrored.Rounded.NavigateNext,
                    contentDescription = "Localized description",
                )
            }
        )

        ListItem(
            headlineContent = { Text(stringResource(id = R.string.authentication)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.Lock,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = {
                Icon(
                    Icons.AutoMirrored.Rounded.NavigateNext,
                    contentDescription = "Localized description",
                )
            }
        )

        ListItem(
            headlineContent = { Text(stringResource(id = R.string.categories)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.Category,
                    contentDescription = "Localized description",
                )
            },
            trailingContent = {
                Icon(
                    Icons.AutoMirrored.Rounded.NavigateNext,
                    contentDescription = "Localized description",
                )
            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun GeneralSectionPreview() {
    GeneralSection()
}