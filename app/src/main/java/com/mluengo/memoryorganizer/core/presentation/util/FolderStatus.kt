package com.mluengo.memoryorganizer.core.presentation.util

import com.mluengo.memoryorganizer.R

enum class FolderStatus(val statusResId: Int) {
    NONE(R.string.new_folders_status_none),
    TODO(R.string.new_folders_status_todo),
    IN_PROGRESS(R.string.new_folders_status_in_progress),
    DONE(R.string.new_folders_status_done),
}