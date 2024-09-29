package com.mluengo.memoryorganizer.ui.screens.folders

import com.mluengo.memoryorganizer.domain.model.Folder

data class FoldersOverviewState(
    val createdFolders: List<Folder> = emptyList()
)
