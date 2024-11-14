package com.mluengo.memoryorganizer.core.presentation.util

sealed class UiEvent {
    data object Success: UiEvent()
    data object NavigateUp: UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}