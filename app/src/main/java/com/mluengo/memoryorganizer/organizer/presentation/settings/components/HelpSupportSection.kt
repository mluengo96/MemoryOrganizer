package com.mluengo.memoryorganizer.organizer.presentation.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.NavigateNext
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material.icons.rounded.WavingHand
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun HelpSupportSection(

) {
    val spacing = LocalSpacing.current

    Column {
        Text(
            text = stringResource(id = R.string.help_support_title),
            textAlign = TextAlign.Start,
            style = MemoryOrganizerTypography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        ListItem(
            headlineContent = { Text(stringResource(id = R.string.about_the_developer)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.Person,
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
            headlineContent = { Text(stringResource(id = R.string.report_bug)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.BugReport,
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
            headlineContent = { Text(stringResource(id = R.string.request_feature)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.WavingHand,
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
            headlineContent = { Text(stringResource(id = R.string.rate)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.StarRate,
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
            headlineContent = { Text(stringResource(id = R.string.share)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.Share,
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
            headlineContent = { Text(stringResource(id = R.string.follow_socials)) },
            leadingContent = {
                Icon(
                    Icons.Rounded.Apps,
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
fun HelpSupportSectionPreview() {
    HelpSupportSection()
}