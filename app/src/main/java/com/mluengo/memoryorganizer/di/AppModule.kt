package com.mluengo.memoryorganizer.di

import com.mluengo.memoryorganizer.core.presentation.Folders2PaneViewModel
import com.mluengo.memoryorganizer.organizer.presentation.bookmarks.BookmarkListViewModel
import com.mluengo.memoryorganizer.organizer.presentation.folder_detail.FolderDetailViewModel
import com.mluengo.memoryorganizer.organizer.presentation.folder_overview.FoldersOverviewViewModel
import com.mluengo.memoryorganizer.organizer.presentation.new_folder.NewFolderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModel { FoldersOverviewViewModel(get(), get()) }
    viewModel { FolderDetailViewModel(get(), get()) }
    viewModelOf(::Folders2PaneViewModel)
    viewModelOf(::BookmarkListViewModel)
    viewModelOf(::NewFolderViewModel)
}