package com.mluengo.memoryorganizer.ui.components.settings_sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSection(

) {
    val spacing = LocalSpacing.current

    Column {
        Text(
            text = stringResource(id = R.string.theme_title),
            textAlign = TextAlign.Start,
            style = MemoryOrganizerTypography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        var selectedIndex by remember { mutableStateOf(0) }
        val options = listOf(
            stringResource(id = R.string.system_theme),
            stringResource(id = R.string.light_theme),
            stringResource(id = R.string.dark_theme)
        )

        SingleChoiceSegmentedButtonRow (
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex
                ) {
                    Text(label)
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun ThemeSectionPreview() {
    ThemeSection()
}