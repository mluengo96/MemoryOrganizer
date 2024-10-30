package com.mluengo.memoryorganizer.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mluengo.memoryorganizer.R
import com.mluengo.memoryorganizer.ui.components.settings_sections.GeneralSection
import com.mluengo.memoryorganizer.ui.components.settings_sections.HelpSupportSection
import com.mluengo.memoryorganizer.ui.components.settings_sections.ThemeSection
import com.mluengo.memoryorganizer.ui.theme.LocalSpacing
import com.mluengo.memoryorganizer.ui.theme.MemoryOrganizerTypography

@Composable
fun SettingsScreen() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = spacing.spaceLarge,
                bottom = spacing.default,
                start = spacing.spaceMedium,
                end = spacing.spaceMedium
            )
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.settings_title),
            textAlign = TextAlign.Start,
            style = MemoryOrganizerTypography.displayLarge,
        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
        ThemeSection()
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        GeneralSection()
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        HelpSupportSection()
    }
}

@Preview(showBackground = true, device = "id:pixel_7a")
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}