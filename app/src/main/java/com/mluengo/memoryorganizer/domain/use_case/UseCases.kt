package com.mluengo.memoryorganizer.domain.use_case

data class UseCases(
    val addFolder: AddFolder,
    val deleteFolder: DeleteFolder,
    val getFolders: GetFolders,
    val getFolderDetail: GetFolderDetail,

    /*val addItem: AddItem,
    val deleteItem: DeleteItem,*/
    val getBookmarks: GetBookmarks,
    val getBookmarksFromFolder: GetBookmarksFromFolder,
)
