package com.mluengo.memoryorganizer.di

import android.app.Application
import androidx.room.Room
import com.mluengo.memoryorganizer.core.data.local.AppDatabase
import com.mluengo.memoryorganizer.core.data.local.Converters
import com.mluengo.memoryorganizer.organizer.data.local.repository.FolderDataSourceImpl
import com.mluengo.memoryorganizer.organizer.data.local.repository.ItemRepositoryImpl
import com.mluengo.memoryorganizer.organizer.domain.repository.FolderDataSource
import com.mluengo.memoryorganizer.organizer.domain.repository.ItemRepository
import com.squareup.moshi.Moshi
import org.koin.dsl.module

private val converter = Converters(Moshi.Builder().build())

fun provideDatabase(app: Application): AppDatabase {
    return Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "bookmark_db"
    )
        .addTypeConverter(converter)
        .build()
}

fun provideFolderRepository(
    db: AppDatabase
): FolderDataSource {
    return FolderDataSourceImpl(
        dao = db.foldersDao
    )
}

fun provideBookmarkRepository(
    db: AppDatabase
): ItemRepository {
    return ItemRepositoryImpl(
        dao = db.bookmarksDao
    )
}

val dataModule = module {
    single { provideDatabase(get()) }
    single { provideFolderRepository(get()) }
    single { provideBookmarkRepository(get()) }
}