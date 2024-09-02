package com.mluengo.memoryorganizer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberAppState(
): AppState {
    return remember {
        AppState()
    }
}

@Stable
class AppState {
    var shouldShowBottomBar by mutableStateOf(true)
        private set

    fun setShowBottomBar(shouldShow: Boolean) {
        shouldShowBottomBar = shouldShow
    }
}