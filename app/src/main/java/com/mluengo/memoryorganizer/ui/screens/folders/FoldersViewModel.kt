package com.mluengo.memoryorganizer.ui.screens.folders

import androidx.lifecycle.ViewModel
import com.mluengo.memoryorganizer.domain.Preferences
import com.mluengo.memoryorganizer.domain.use_case.UseCases
import com.mluengo.memoryorganizer.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class FoldersViewModel @Inject constructor(
    //preferences: Preferences,
    private val useCases: UseCases
): ViewModel() {
    /*var state by mutableStateOf(TrackerOverviewState())
        private set*/

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getFolders()
    }

    private fun getFolders() {
        val foldersResult = useCases.getFolders

    }
}