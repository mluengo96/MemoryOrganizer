package com.mluengo.memoryorganizer.di

import com.mluengo.memoryorganizer.organizer.domain.repository.FolderRepository
import com.mluengo.memoryorganizer.organizer.domain.repository.ItemRepository
import com.mluengo.memoryorganizer.organizer.domain.use_case.AddFolder
import com.mluengo.memoryorganizer.organizer.domain.use_case.DeleteFolder
import com.mluengo.memoryorganizer.organizer.domain.use_case.GetBookmarks
import com.mluengo.memoryorganizer.organizer.domain.use_case.GetBookmarksFromFolder
import com.mluengo.memoryorganizer.organizer.domain.use_case.GetFolderDetail
import com.mluengo.memoryorganizer.organizer.domain.use_case.GetFolders
import com.mluengo.memoryorganizer.organizer.domain.use_case.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideUseCases(
        folderRepository: FolderRepository,
        itemRepository: ItemRepository,
        //preferences: Preferences
    ): UseCases {
        return UseCases(
            addFolder = AddFolder(repository = folderRepository),
            deleteFolder = DeleteFolder(repository = folderRepository),
            getFolderDetail = GetFolderDetail(repository = folderRepository),
            getFolders = GetFolders(repository = folderRepository),
            getBookmarks = GetBookmarks(repository = itemRepository),
            getBookmarksFromFolder = GetBookmarksFromFolder(repository = itemRepository),
            /*addItem = AddItem(repository = itemRepository),
            deleteItem = DeleteItem(repository = itemRepository),*/
        )
    }
}